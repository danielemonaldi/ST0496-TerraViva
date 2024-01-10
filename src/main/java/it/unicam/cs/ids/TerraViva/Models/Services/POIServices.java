package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Handlers.RequestsHandler;
import it.unicam.cs.ids.TerraViva.Models.Requests.MultiStatusRequest;
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

    public POI create(String name,
                      double latitude,
                      double longitude,
                      @Nullable Date expire,
                      String author) throws Exception {
        Optional<User> user = usersRepository.findByUsername(author);
        if(user.isEmpty()) throw new Exception("Author not found");
        Date creation = new Date(System.currentTimeMillis());
        POI poi = new POI(name, latitude, longitude, creation, expire, user.get().getUsername());

        poiRepository.save(poi);
        poiRepository.findAll().forEach(System.out::println);
        for(POI o : poiRepository.findAll()) {
            System.out.println(o.getAuthor());
            System.out.println(o.getCreation());
            System.out.println(o.getExpire());
            System.out.println(o.getName());
        }
        return poi;
    }

    public void publish(String author, POI poi) throws Exception {
        Optional<User> user = usersRepository.findByUsername(author);
        if(user.isEmpty()) throw new Exception("Author not found");
        Date creation = new Date(System.currentTimeMillis());
        MultiStatusRequest request = new AuthorizationRequest(user.get().getUsername(), poi, creation);
        reqHandler.submit(request);
    }
}
