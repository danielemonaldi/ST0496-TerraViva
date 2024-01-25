package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.MultimediaContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.TextualContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ContentServices {
    @Autowired
    private RequestServices requestServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private AuthorizationRepository<Content> contentRepository;

    public TextualContent createTextualContent(User author, String data) {
        return new TextualContent(author, data);
    }

    public MultimediaContent createMultimediaContent(User author, String data) {
        return new MultimediaContent(author, data);
    }

    public void delete(Content content) {
        contentRepository.delete(content);
    }

    public void publish(Content content) {}

    public void report(Content content) {}

    public void saveForLater(Content content) {}

    public void confirmNew(Content content, POI reference) {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(content.getAuthor(), content, creation);
        requestServices.submit(request);
        reference.addContent(content);
        poiServices.save(reference);
    }
}
