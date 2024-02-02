package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.CulturalPOI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContestServicesTest {

    @Autowired
    private ContestServices contestServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    AuthenticationServices authenticationServices;

    @Autowired
    private AuthorizationRepository<Contest> contestRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void confirmNew() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        Contest contest = new Contest(author, poi);
        contest.setName("testContest");

        contestServices.confirmNew(contest, poi);

        assertTrue(contestRepository.findById(contest.getID()).isPresent());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void delete() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        Contest contest = new Contest(author, poi);
        contest.setName("testContest");

        contestServices.confirmNew(contest, poi);
        contestServices.delete(contest);

        assertTrue(contestRepository.findById(contest.getID()).isEmpty());
    }
}