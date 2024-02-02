package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.EventPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class POIServicesTest {

    @Autowired
    private POIServices poiServices;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DirtiesContext
    @Transactional
    void confirmNew() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        EventPOI poi = poiServices.createEventPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poi.setCreation(new Date());
        poi.setExpire(new Date());
        poiServices.confirmNew(poi);

        poiRepository.save(poi);
        assertTrue(poiRepository.findById(poi.getID()).isPresent());
    }

    @Test
    @DirtiesContext
    @Transactional
    void delete() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        EventPOI poi = poiServices.createEventPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poi.setCreation(new Date());
        poi.setExpire(new Date());
        poiServices.confirmNew(poi);

        poiRepository.save(poi);
        assertTrue(poiRepository.findById(poi.getID()).isPresent());

        poiServices.delete(poi);
        assertTrue(poiRepository.findById(poi.getID()).isEmpty());
    }
}