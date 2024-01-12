package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class POIServices {
    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthRequestServices requestServices;

    public void publish(POI poi) throws Exception {
        Date creation = new Date(System.currentTimeMillis());
        AuthorizationRequest request = new AuthorizationRequest(poi.getAuthor(), poi, creation);
        poiRepository.save(poi);
        requestServices.submit(request);
    }
}
