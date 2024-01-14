package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.MultimediaContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.TextualContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;
import it.unicam.cs.ids.TerraViva.Services.MultimediaContentServices;
import it.unicam.cs.ids.TerraViva.Services.TextualContentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    @Autowired
    TextualContentServices textualContentServices;

    @Autowired
    MultimediaContentServices multimediaContentServices;

    @PostMapping("/creation/content/textual")
    public ResponseEntity<String> create(@RequestBody TextualContent content){
        try {
            textualContentServices.publish(content);
            return ResponseEntity.status(HttpStatus.CREATED).body("Content uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error uploading content: " + e.getMessage());
        }
    }

    @PostMapping("/creation/content/multimedia")
    public ResponseEntity<String> create(@RequestBody MultimediaContent content){
        try {
            multimediaContentServices.publish(content);
            return ResponseEntity.status(HttpStatus.CREATED).body("Content uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error uploading content: " + e.getMessage());
        }
    }
}
