package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class POIController {

    @Autowired
    POIServices poiServices;

    @PostMapping("/creation/POI")
    public ResponseEntity<String> create(@RequestBody POI poi){
        try {
            poiServices.publish(poi);
            return ResponseEntity.status(HttpStatus.CREATED).body("POI created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating POI: " + e.getMessage());
        }
    }
}
