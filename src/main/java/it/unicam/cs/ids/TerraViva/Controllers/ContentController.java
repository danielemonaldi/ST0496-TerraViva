package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Services.ContentServices;
import it.unicam.cs.ids.TerraViva.Services.ContestServices;
import it.unicam.cs.ids.TerraViva.Services.POIServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContentController {

    @Autowired
    ContentServices contentServices;

    @Autowired
    POIServices poiServices;

    @Autowired
    ContestServices contestServices;

    @PostMapping("/creation/poi-content")
    public ResponseEntity<String> createPoiContent(@RequestBody Content content, @RequestParam long reference) {
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

    @PostMapping("/creation/contest-content")
    public ResponseEntity<String> createContestContent(@RequestBody Content content, @RequestParam long reference) {
        try {
            Optional<Contest> contest = contestServices.getContest(reference);
            if(contest.isPresent()) {
                contentServices.confirmNew(content, contest.get());
                return ResponseEntity.status(HttpStatus.CREATED).body("Content uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error uploading content: no reference entity with the given ID");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error uploading content: " + e.getMessage());
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<String> createContestContent(@RequestBody Content content, @RequestParam String social) {
        try {
            contentServices.publish(content, social);
            return ResponseEntity.status(HttpStatus.OK).body("Content published successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error publishing content: " + e.getMessage());
        }
    }

    @GetMapping("/content/getContents/{id}")
    public ResponseEntity<List<Content>> getContents(@PathVariable("id") long id) {
        return new ResponseEntity<>(contentServices.getContents(id), HttpStatus.OK);
    }
}