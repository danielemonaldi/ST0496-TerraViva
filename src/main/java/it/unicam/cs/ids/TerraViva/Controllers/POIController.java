package it.unicam.cs.ids.TerraViva.Controllers;

import it.unicam.cs.ids.TerraViva.Models.Contributable;
import it.unicam.cs.ids.TerraViva.Models.POI;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class POIController {
    @PostMapping("/creation/POI")
    public void create(@RequestParam(name="name") String name,
                       @RequestParam(name="latitude") double latitude,
                       @RequestParam(name="longitude") double longitude,
                       @RequestParam(name="expire") @Nullable Date expire,
                       @RequestParam(name="author") String author){
        Date creation = new Date(System.currentTimeMillis());
        POI poi = new POI(name, latitude, longitude, creation, expire, author);
        System.out.println(poi.getCreation());
        System.out.println(poi.getExpire());
    }
}
