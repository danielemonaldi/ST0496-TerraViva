package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
        AuthorizationRequest request = new AuthorizationRequest();
        authRequestServices.submit(request);

        Optional<AuthorizationRequest> savedRequest = authRequestRepository.findById(request.getID());

        assertTrue(savedRequest.isPresent());
        assertEquals(RequestStatus.PENDING, savedRequest.get().getStatus());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testAccept() throws Exception {
        AuthorizationRequest request = new AuthorizationRequest();
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
        AuthorizationRequest request = new AuthorizationRequest();
        authRequestRepository.save(request);

        long requestId = request.getID();
        authRequestServices.reject(requestId);

        Optional<AuthorizationRequest> rejectedRequest = authRequestRepository.findById(requestId);

        assertTrue(rejectedRequest.isPresent());
        assertEquals(RequestStatus.REJECTED, rejectedRequest.get().getStatus());
    }
}
