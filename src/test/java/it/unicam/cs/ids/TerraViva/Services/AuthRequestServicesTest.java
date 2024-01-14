package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthRequestServicesTest {

    @Autowired
    private AuthRequestServices authRequestServices;

    @Autowired
    private RequestRepository<AuthorizationRequest> authRequestRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void testPendingRequests() {
        List<AuthorizationRequest> pendingRequests = authRequestServices.pendingRequests();
        assertNotNull(pendingRequests);
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testSubmit() {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);
        POI content = new POI("POI Reference", 1.0, 2.0, new Date(), null, author);

        AuthorizationRequest request = new AuthorizationRequest(author, content, new Date(System.currentTimeMillis()));
        authRequestServices.submit(request);

        Optional<AuthorizationRequest> savedRequest = authRequestRepository.findById(request.getID());

        assertTrue(savedRequest.isPresent());
        assertEquals(RequestStatus.PENDING, savedRequest.get().getStatus());
        assertEquals(content, savedRequest.get().getContent());
        assertEquals(author, savedRequest.get().getAuthor());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testAccept() throws Exception {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);
        POI content = new POI("POI Reference", 1.0, 2.0, new Date(), null, author);

        AuthorizationRequest request = new AuthorizationRequest(author, content, new Date(System.currentTimeMillis()));
        authRequestRepository.save(request);

        long requestId = request.getID();
        authRequestServices.accept(requestId);

        Optional<AuthorizationRequest> acceptedRequest = authRequestRepository.findById(requestId);

        assertTrue(acceptedRequest.isPresent());
        assertEquals(RequestStatus.APPROVED, acceptedRequest.get().getStatus());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testReject() throws Exception {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);
        POI content = new POI("POI Reference", 1.0, 2.0, new Date(), null, author);

        AuthorizationRequest request = new AuthorizationRequest(author, content, new Date(System.currentTimeMillis()));        authRequestRepository.save(request);

        long requestId = request.getID();
        authRequestServices.reject(requestId);

        Optional<AuthorizationRequest> rejectedRequest = authRequestRepository.findById(requestId);

        assertTrue(rejectedRequest.isPresent());
        assertEquals(RequestStatus.REJECTED, rejectedRequest.get().getStatus());
    }
}
