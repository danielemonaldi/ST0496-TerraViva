package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class POIController {

    @Autowired
    POIServices poiServices;

    @PostMapping("/POI")
    public ResponseEntity<String> create(@RequestBody POI poi){
        try {
            poiServices.confirmNew(poi);
            return ResponseEntity.status(HttpStatus.CREATED).body("\"POI created successfully\"");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"Error creating POI: " + e.getMessage() + "\"");
        }
    }

    @GetMapping("/POI")
    public ResponseEntity<List<POI>> getAllPOI() {
        return ResponseEntity.status(HttpStatus.OK).body(poiServices.getAllPOI());
    }

    @GetMapping("/POI/{id}")
    public ResponseEntity<Optional<POI>> getInfo(@PathVariable("id") long id){
        return new ResponseEntity<>(poiServices.getPOI(id), HttpStatus.OK);
    }
}
