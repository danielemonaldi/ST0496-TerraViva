package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.MultimediaContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.EventPOI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class MultimediaContentServicesTest {

    @Autowired
    private ContentServices multimediaContentServices;

    @Autowired
    private AuthorizationRepository<MultimediaContent> multimediaContentRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void testPublish() throws Exception {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);
        MultimediaContent multimediaContent = new MultimediaContent(author, "data");

        EventPOI poiReference = new EventPOI(0.0, 0.0, author);
        poiReference.setName("test");
        poiReference.setCreation(new Date());
        poiReference.setExpire(new Date());
        poiReference.authorize();

        multimediaContentServices.confirmNew(multimediaContent, poiReference);

        Optional<MultimediaContent> content = multimediaContentRepository.findById(multimediaContent.getID());
        assertTrue(content.isPresent());
        assertEquals(multimediaContent, content.get());
        assertEquals(multimediaContent.getAuthor(), content.get().getAuthor());
        assertEquals(multimediaContent.getType(), content.get().getType());
    }
}
