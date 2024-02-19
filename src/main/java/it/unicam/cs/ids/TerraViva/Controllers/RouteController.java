package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Route;
import it.unicam.cs.ids.TerraViva.Services.RouteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RouteController {

    @Autowired
    RouteServices routeServices;

    @PostMapping("/route/creation")
    public ResponseEntity<String> create(@RequestBody Route route){
        try {
            routeServices.confirmNew(route);
            return ResponseEntity.status(HttpStatus.CREATED).body("Route created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating route: " + e.getMessage());
        }
    }

    @GetMapping("/route/getAllRoutes")
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.status(HttpStatus.OK).body(routeServices.getAllRoutes());
    }
}
