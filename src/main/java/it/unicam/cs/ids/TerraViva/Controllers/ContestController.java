package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Route;
import it.unicam.cs.ids.TerraViva.Services.ContestServices;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContestController {

    @Autowired
    ContestServices contestServices;

    @Autowired
    POIServices poiServices;

    @PostMapping("/contest")
    public ResponseEntity<String> create(@RequestBody Contest contest, @RequestParam long reference) {
        try {
            Optional<POI> poi = poiServices.getPOI(reference);
            if(poi.isPresent()) {
                contestServices.confirmNew(contest, poi.get());
                return ResponseEntity.status(HttpStatus.CREATED).body("\"Contest created successfully\"");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("\"Error uploading contest: no reference entity with the given ID\"");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"Error uploading contest: " + e.getMessage() + "\"");
        }
    }

    @DeleteMapping("/contest")
    public ResponseEntity<String> delete(@RequestBody Contest contest) {
        try {
            contestServices.delete(contest);
            return ResponseEntity.status(HttpStatus.CREATED).body("\"Contest deleted successfully\"");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\"Error deleting contest: " + e.getMessage() + "\"");
        }
    }

    @GetMapping("/contest")
    public ResponseEntity<List<Contest>> getAllContests() {
        return ResponseEntity.status(HttpStatus.OK).body(contestServices.getAllContests());
    }

    @GetMapping("/contest/{id}")
    public ResponseEntity<List<Contest>> getContests(@PathVariable("id") long id) {
        return new ResponseEntity<>(contestServices.getContests(id), HttpStatus.OK);
    }
}