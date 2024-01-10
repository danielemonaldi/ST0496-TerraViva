package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.RequestsBodies.POICreationRequestBody;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Models.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class POIController {

    @Autowired
    POIServices poiServices;

    @PostMapping("/creation/POI")
    public void create(@RequestBody POICreationRequestBody body){
        try{
            POI poi = poiServices.create(body.name(), body.latitude(), body.longitude(), body.expire(), body.author());
            poiServices.publish(body.author(), poi);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
