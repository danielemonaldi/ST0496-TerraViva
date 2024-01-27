package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Route;
import it.unicam.cs.ids.TerraViva.Services.RouteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    @Autowired
    RouteServices routeServices;

    @PostMapping("/creation/route")
    public ResponseEntity<String> create(@RequestBody Route route){
        try {
            routeServices.confirmNew(route);
            return ResponseEntity.status(HttpStatus.CREATED).body("Route created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating route: " + e.getMessage());
        }
    }
}
