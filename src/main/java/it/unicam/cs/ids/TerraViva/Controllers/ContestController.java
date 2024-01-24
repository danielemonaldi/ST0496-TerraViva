package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Services.ContestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContestController {

    @Autowired
    ContestServices contestServices;

    @PostMapping("/creation/contest")
    public ResponseEntity<String> create(@RequestBody Contest contest) {
        try {
            contestServices.publish(contest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contest created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating contest: " + e.getMessage());
        }
    }

    @PostMapping("/deletion/contest")
    public ResponseEntity<String> delete(@RequestBody long idContest) {
        try {
            contestServices.delete(idContest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Contest deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error deleting contest: " + e.getMessage());
        }
    }
}