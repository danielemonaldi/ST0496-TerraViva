package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Services.ContentServices;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ContentController {
    @Autowired
    ContentServices contentServices;

    @Autowired
    POIServices poiServices;

    @PostMapping("/creation/content")
    public ResponseEntity<String> create(@RequestBody Content content, @RequestParam long reference){
        try {
            Optional<POI> poi = poiServices.getPOI(reference);
            if(poi.isPresent()) {
                contentServices.confirmNew(content, poi.get());
                return ResponseEntity.status(HttpStatus.CREATED).body("Content uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error uploading content: no reference entity with the given ID");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error uploading content: " + e.getMessage());
        }
    }
}
