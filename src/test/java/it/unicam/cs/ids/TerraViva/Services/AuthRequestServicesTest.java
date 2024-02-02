package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.CulturalPOI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.RequestRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
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
    private UsersRepository usersRepository;

    @Autowired
    private RequestServices authRequestServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private RequestRepository<AuthorizationRequest> authRequestRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void testPendingRequests() {
        List<MultiStatusRequest> pendingRequests = authRequestServices.getRequests();
        assertNotNull(pendingRequests);
    }

    @Test
    @DirtiesContext
    @Transactional
    public void submit() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        AuthorizationRequest request = new AuthorizationRequest(author, poi, new Date(System.currentTimeMillis()));
        authRequestServices.submit(request);

        Optional<AuthorizationRequest> savedRequest = authRequestRepository.findById(request.getID());
        assertTrue(savedRequest.isPresent());
        assertEquals(RequestStatus.PENDING, savedRequest.get().getStatus());
        assertEquals(poi, savedRequest.get().getContent());
        assertEquals(author, savedRequest.get().getAuthor());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void accept() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        AuthorizationRequest request = new AuthorizationRequest(author, poi, new Date(System.currentTimeMillis()));
        authRequestRepository.save(request);
        authRequestServices.accept(request);

        Optional<AuthorizationRequest> acceptedRequest = authRequestRepository.findById(request.getID());
        assertTrue(acceptedRequest.isPresent());
        assertEquals(RequestStatus.APPROVED, acceptedRequest.get().getStatus());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void reject() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        AuthorizationRequest request = new AuthorizationRequest(author, poi, new Date(System.currentTimeMillis()));
        authRequestRepository.save(request);

        authRequestServices.reject(request);

        Optional<AuthorizationRequest> rejectedRequest = authRequestRepository.findById(request.getID());
        assertTrue(rejectedRequest.isPresent());
        assertEquals(RequestStatus.REJECTED, rejectedRequest.get().getStatus());
    }
}