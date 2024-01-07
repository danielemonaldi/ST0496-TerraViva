package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Models.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class POIServices {
    public static AuthorizationRequest create(String name,
                                              double latitude,
                                              double longitude,
                                              @Nullable Date expire,
                                              String author){
        Date creation = new Date(System.currentTimeMillis());
        POI poi = new POI(name, latitude, longitude, creation, expire, author);
        return new AuthorizationRequest(author, poi, creation);
    }
}
