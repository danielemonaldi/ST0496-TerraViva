package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Handlers.RequestsHandler;
import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
import it.unicam.cs.ids.TerraViva.Models.RequestsBodies.POICreationRequestBody;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class POIServices {
    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RequestsHandler reqHandler;

    private User getUserFromUsername(String username) throws Exception {
        Optional<User> user = usersRepository.findByUsername(username);
        if(user.isEmpty()) throw new Exception("Author not found");
        return user.get();
    }

    public POI create(POICreationRequestBody template) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        POI poi = new POI(template.getName(),
                template.getLatitude(),
                template.getLongitude(),
                creation,
                template.getExpire(),
                getUserFromUsername(template.getAuthor()));
        poiRepository.save(poi);
        publish(template.getAuthor(), poi);
        return poi;
    }

    public void publish(String author, POI poi) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        MultiStatusRequest request = new AuthorizationRequest(getUserFromUsername(author), poi, creation);
        reqHandler.submit(request);
    }
}
