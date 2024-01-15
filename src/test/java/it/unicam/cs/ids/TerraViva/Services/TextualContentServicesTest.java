package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.TextualContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
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
public class TextualContentServicesTest {

    @Autowired
    private TextualContentServices textualContentServices;

    @Autowired
    private AuthorizationRepository<TextualContent> textualContentRepository;

    @Test
    @Transactional
    @DirtiesContext
    public void testPublish() throws Exception {
        User author = new User("testUsername", "123", "test@gmail.com", Role.AUTHORIZED_TOURIST);
        TextualContent textualContent = new TextualContent(author, "data");

        POI poiReference = new POI("POI Reference", 1.0, 2.0, new Date(), null, author);
        poiReference.authorize();
        textualContent.setRefersTo(poiReference);

        textualContentServices.publish(textualContent);

        Optional<TextualContent> content = textualContentRepository.findById(textualContent.getID());
        assertTrue(content.isPresent());
        assertEquals(textualContent, content.get());
        assertEquals(textualContent.getAuthor(), content.get().getAuthor());
        assertEquals(textualContent.getType(), content.get().getType());
        assertEquals(textualContent.getRefersTo(), content.get().getRefersTo());
    }
}
