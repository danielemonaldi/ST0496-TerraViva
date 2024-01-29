package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.*;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.PromotionRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.CulturalPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.EventPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import it.unicam.cs.ids.TerraViva.Services.AuthenticationServices;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    private AuthorizationRepository<AuthorizationEntity> authorizationRepository;

    @Autowired
    private RequestRepository<AuthorizationRequest> authRequestRepository;

    @Autowired
    private RequestRepository<PromotionRequest> promRequestRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private POIServices poiServices;

    @Test
    @DirtiesContext
    @Transactional
    public void testAuthorizationEntityRepository() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        Optional<AuthorizationEntity> retrievedPoi = authorizationRepository.findById(poi.getID());
        assertTrue(retrievedPoi.isPresent());
        assertEquals("testPOI", ((POI) retrievedPoi.get()).getName());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testAuthorizationRequestRepository() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        AuthorizationRequest authRequest = new AuthorizationRequest(author, poi, new Date());
        AuthorizationRequest savedAuthRequest = authRequestRepository.save(authRequest);

        Optional<AuthorizationRequest> retrievedAuthRequest = authRequestRepository.findById(savedAuthRequest.getID());

        assertTrue(retrievedAuthRequest.isPresent());
        assertEquals(RequestStatus.PENDING, retrievedAuthRequest.get().getStatus());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testPromotionRequestRepository() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        PromotionRequest promRequest = new PromotionRequest(author, "Test motivation", Role.CONTRIBUTOR, new Date());
        PromotionRequest savedPromRequest = promRequestRepository.save(promRequest);

        Optional<PromotionRequest> retrievedPromRequest = promRequestRepository.findById(savedPromRequest.getID());

        assertTrue(retrievedPromRequest.isPresent());
        assertEquals(RequestStatus.PENDING, retrievedPromRequest.get().getStatus());
    }
}