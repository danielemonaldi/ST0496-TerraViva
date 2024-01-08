package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.Services.POIServices;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class POIController {

    @Autowired
    POIServices poiServices;

    @PostMapping("/creation/POI")
    public void create(@RequestParam(name="name") String name,
                       @RequestParam(name="latitude") double latitude,
                       @RequestParam(name="longitude") double longitude,
                       @RequestParam(name="expire") @Nullable Date expire,
                       @RequestParam(name="author") String author){
        try{
            AuthorizationRequest request = poiServices.create(name, latitude, longitude, expire, author);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
