package it.unicam.cs.ids.TerraViva.Repository;

import it.unicam.cs.ids.TerraViva.Models.*;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.PromotionRequest;
import it.unicam.cs.ids.TerraViva.Models.Requests.RequestStatus;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.EventPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
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

    @Test
    @DirtiesContext
    @Transactional
    public void testAuthorizationEntityRepository() {
        User user = new User("testUser", "password", "test@example.com", Role.AUTHORIZED_TOURIST);
        user = usersRepository.save(user);

        EventPOI poi = new EventPOI(0.0, 0.0, user);
        poi.setName("Test POI");
        poi.setCreation(new Date());
        poi.setExpire(new Date());
        AuthorizationEntity savedPoi = authorizationRepository.save(poi);

        Optional<AuthorizationEntity> retrievedPoi = authorizationRepository.findById(savedPoi.getID());

        assertTrue(retrievedPoi.isPresent());
        assertEquals("Test POI", ((POI) retrievedPoi.get()).getName());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testAuthorizationRequestRepository() {
        User user = new User("testUser", "password", "test@example.com", Role.AUTHORIZED_TOURIST);
        user = usersRepository.save(user);

        EventPOI poi = new EventPOI(0.0, 0.0, user);
        poi.setName("test");
        poi.setCreation(new Date());
        poi.setExpire(new Date());
        poi = authorizationRepository.save(poi);

        AuthorizationRequest authRequest = new AuthorizationRequest(user, poi, new Date());
        AuthorizationRequest savedAuthRequest = authRequestRepository.save(authRequest);

        Optional<AuthorizationRequest> retrievedAuthRequest = authRequestRepository.findById(savedAuthRequest.getID());

        assertTrue(retrievedAuthRequest.isPresent());
        assertEquals(RequestStatus.PENDING, retrievedAuthRequest.get().getStatus());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void testPromotionRequestRepository() {
        User user = new User("testUser", "password", "test@example.com", Role.AUTHORIZED_TOURIST);
        user = usersRepository.save(user);

        PromotionRequest promRequest = new PromotionRequest(user, "Test motivation", Role.CONTRIBUTOR, new Date());
        PromotionRequest savedPromRequest = promRequestRepository.save(promRequest);

        Optional<PromotionRequest> retrievedPromRequest = promRequestRepository.findById(savedPromRequest.getID());

        assertTrue(retrievedPromRequest.isPresent());
        assertEquals(RequestStatus.PENDING, retrievedPromRequest.get().getStatus());
    }
}

