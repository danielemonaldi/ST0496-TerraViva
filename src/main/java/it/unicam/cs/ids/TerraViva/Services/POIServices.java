package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.*;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class POIServices {
    @Autowired
    private AuthorizationRepository<POI> poiRepository;

    @Autowired
    private RequestServices requestServices;

    public CulturalPOI createCulturalPOI(double latitude, double longitude, User author) {
        return new CulturalPOI(latitude, longitude, author);
    }

    public EventPOI createEventPOI(double latitude, double longitude, User author) {
        return new EventPOI(latitude, longitude, author);
    }

    public RecreationalPOI createRecreationalPOI(double latitude, double longitude, User author) {
        return new RecreationalPOI(latitude, longitude, author);
    }

    public CommercialPOI createCommercialPOI(double latitude, double longitude, User author) {
        return new CommercialPOI(latitude, longitude, author);
    }

    public void delete(POI poi) {
        poiRepository.delete(poi);
    }

    public List<POI> getPOI() {
        return poiRepository.findAll();
    }

    public Optional<POI> getPOI(long ID) {
        return poiRepository.findById(ID);
    }

    public void confirmNew(POI poi) {
        // if(!poi.getAuthor().getAuthorities().contains(Role.AUTHORIZED_ENTERTAINER) &&
        //        !poi.getAuthor().getAuthorities().contains(Role.AUTHORIZED_CONTRIBUTOR) &&
        //        !poi.authorized()) {
            Date creation = new Date(System.currentTimeMillis());
            AuthorizationRequest request = new AuthorizationRequest(poi.getAuthor(), poi, creation);
            requestServices.submit(request);
        // } else {
        //    poi.authorize();
        // }
        poiRepository.save(poi);
    }

    public void save(POI poi) {
        poiRepository.save(poi);
    }
}
