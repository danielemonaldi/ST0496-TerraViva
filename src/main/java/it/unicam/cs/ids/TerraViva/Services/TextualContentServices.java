package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.TextualContent;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TextualContentServices {
    @Autowired
    private AuthRequestServices requestServices;

    @Autowired
    private AuthorizationRepository<TextualContent> contentRepository;

    public void publish(TextualContent content) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(content.getAuthor(), content, creation);
        contentRepository.save(content);
        requestServices.submit(request);
    }
}
