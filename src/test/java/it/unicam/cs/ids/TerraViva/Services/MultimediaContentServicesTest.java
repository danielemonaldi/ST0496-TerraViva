package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.MultimediaContent;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MultimediaContentServicesTest {

    @Autowired
    private ContentServices contentServices;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorizationRepository<MultimediaContent> multimediaContentRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void confirmNewPOI() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        MultimediaContent multimediaContent = new MultimediaContent(author, "data");

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        contentServices.confirmNew(multimediaContent, poi);

        Optional<MultimediaContent> content = multimediaContentRepository.findById(multimediaContent.getID());
        assertTrue(content.isPresent());
        assertTrue(poi.getContents().contains(multimediaContent));
        assertEquals(multimediaContent, content.get());
        assertEquals(multimediaContent.getAuthor(), content.get().getAuthor());
        assertEquals(multimediaContent.getType(), content.get().getType());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void confirmNewContest() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        MultimediaContent multimediaContent = new MultimediaContent(author, "data");

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        Contest contest = new Contest(author, poi);
        contest.setName("testContest");

        contentServices.confirmNew(multimediaContent, contest);

        Optional<MultimediaContent> content = multimediaContentRepository.findById(multimediaContent.getID());
        assertTrue(content.isPresent());
        assertTrue(contest.getContents().contains(multimediaContent));
        assertEquals(multimediaContent, content.get());
        assertEquals(multimediaContent.getAuthor(), content.get().getAuthor());
        assertEquals(multimediaContent.getType(), content.get().getType());
    }
}