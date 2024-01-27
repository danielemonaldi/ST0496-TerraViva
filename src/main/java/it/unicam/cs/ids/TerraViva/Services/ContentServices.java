package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.*;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
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
    private ContestServices contestServices;

    @Autowired
    private AuthorizationRepository<Content> contentRepository;

    private SocialManager socialManager;

    public TextualContent createTextualContent(User author, String data) {
        return new TextualContent(author, data);
    }

    public MultimediaContent createMultimediaContent(User author, String data) {
        return new MultimediaContent(author, data);
    }

    public void delete(Content content) {
        contentRepository.delete(content);
    }

    public void publish(Content content, String social) {
        switch (social.toLowerCase()) {
            case "instagram":
                InstagramPublisher instagramPublisher = new InstagramPublisher();
                instagramPublisher.publish(content);
                break;
            case "facebook":
                FacebookDecorator facebookPublisher = new FacebookDecorator(this.socialManager);
                facebookPublisher.publish(content);
                break;
            case "x":
                XDecorator xPublisher = new XDecorator(this.socialManager);
                xPublisher.publish(content);
            default:
                throw new IllegalArgumentException(social + " social in not supported");
        }
    }

    public void report(Content content) {}

    public void saveForLater(Content content) {}

    public void confirmNew(Content content, POI reference) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(content.getAuthor(), content, creation);
        requestServices.submit(request);
        reference.addContent(content);
        poiServices.save(reference);
    }

    public void confirmNew(Content content, Contest reference) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(content.getAuthor(), content, creation);
        requestServices.submit(request);
        reference.addContent(content);
        contestServices.save(reference);
    }
}
