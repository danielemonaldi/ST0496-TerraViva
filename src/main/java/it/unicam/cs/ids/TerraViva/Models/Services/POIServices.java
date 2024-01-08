package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Models.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.POIRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Service
public class POIServices {
    @Autowired
    private POIRepository poiRepository;

    @Autowired
    private UsersRepository usersRepository;

    public AuthorizationRequest create(String name,
                                              double latitude,
                                              double longitude,
                                              @Nullable Date expire,
                                              String author) throws Exception {
        Optional<User> user = usersRepository.findByUsername(author);
        if(user.isEmpty()) throw new Exception("Author not found");
        Date creation = new Date(System.currentTimeMillis());
        POI poi = new POI(name, latitude, longitude, creation, expire, user.get().getUsername());

        poiRepository.save(poi);
        return new AuthorizationRequest(user.get(), poi, creation);
    }
}
