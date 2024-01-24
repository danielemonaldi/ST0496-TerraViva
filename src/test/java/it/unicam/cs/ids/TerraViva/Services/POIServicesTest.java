package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.EventPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
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
    POIServices poiServices;

    @Autowired
    AuthorizationRepository<POI> poiRepository;

    @Autowired
    UsersRepository usersRepository;

    @Test
    @Transactional
    @DirtiesContext
    void delete() {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);

        EventPOI poi = poiServices.createEventPOI(0.0, 0.0, author);
        poi.setName("test");
        poi.setCreation(new Date());
        poi.setExpire(new Date());

        poiRepository.save(poi);
        assertTrue(poiRepository.findById(poi.getID()).isPresent());

        poiServices.delete(poi);
        assertTrue(poiRepository.findById(poi.getID()).isEmpty());
    }

    @Test
    @Transactional
    @DirtiesContext
    void getPOI() {
    }

    @Test
    @Transactional
    @DirtiesContext
    void confirmNew() {
    }
}