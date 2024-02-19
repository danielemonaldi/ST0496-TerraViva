package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.TextualContent;
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
public class TextualContentServicesTest {

    @Autowired
    private ContentServices contentServices;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private ContestServices contestServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorizationRepository<TextualContent> textualContentRepository;

    @Test
    @DirtiesContext
    @Transactional
    public void confirmNewPOI() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        TextualContent textualContent = new TextualContent(author, "data");

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);

        contentServices.confirmNew(textualContent, poi);

        Optional<TextualContent> content = textualContentRepository.findById(textualContent.getID());
        assertTrue(content.isPresent());
        assertTrue(poi.getContents().contains(textualContent));
        assertEquals(textualContent, content.get());
        assertEquals(textualContent.getAuthor(), content.get().getAuthor());
        assertEquals(textualContent.getType(), content.get().getType());
    }

    @Test
    @DirtiesContext
    @Transactional
    public void confirmNewContest() throws Exception {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        TextualContent textualContent = new TextualContent(author, "data");

        CulturalPOI poi = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi.setName("testPOI");
        poiServices.confirmNew(poi);;

        Contest contest = new Contest(author, poi);
        contest.setName("testContest");
        contestServices.confirmNew(contest, poi);

        contentServices.confirmNew(textualContent, contest);

        Optional<TextualContent> content = textualContentRepository.findById(textualContent.getID());
        assertTrue(content.isPresent());
        assertTrue(contest.getContents().contains(textualContent));
        assertEquals(textualContent, content.get());
        assertEquals(textualContent.getAuthor(), content.get().getAuthor());
        assertEquals(textualContent.getType(), content.get().getType());
    }
}
