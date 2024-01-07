package it.unicam.cs.ids.TerraViva.Models.Services;

import it.unicam.cs.ids.TerraViva.Models.POI;
import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class POIServicesTest {

    @Test
    public void createAuthorizationRequestForPOI() {
        String name = "POI Name";
        double latitude = 40.7128;
        double longitude = -74.0060;
        Date expire = new Date(System.currentTimeMillis() + 86400000); // Set expire date to 1 day from now
        String author = "TestUser";

        AuthorizationRequest authorizationRequest = POIServices.create(name, latitude, longitude, expire, author);

        Assertions.assertNotNull(authorizationRequest);
        Assertions.assertEquals(author, authorizationRequest.getAuthor());

        // Assuming POI creation happens internally within the service
        POI poi = (POI) authorizationRequest.getContent();
        Assertions.assertNotNull(poi);
        Assertions.assertEquals(name, poi.getName());
        Assertions.assertEquals(latitude, poi.getLatitude());
        Assertions.assertEquals(longitude, poi.getLongitude());
        Assertions.assertEquals(author, poi.getAuthor());
        Assertions.assertTrue(poi.getCreation().before(new Date())); // Assuming creation date is in the past or present
        Assertions.assertEquals(expire, poi.getExpire());
        Assertions.assertFalse(poi.authorized()); // Assuming authorization is initially false for a new POI
    }
}