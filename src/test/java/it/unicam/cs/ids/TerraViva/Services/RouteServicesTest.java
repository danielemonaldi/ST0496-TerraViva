package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.CulturalPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Route;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import it.unicam.cs.ids.TerraViva.Repository.UsersRepository;
import it.unicam.cs.ids.TerraViva.Security.Authentication.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteServicesTest {

    @Autowired
    private RouteServices routeServices;

    @Autowired
    private POIServices poiServices;

    @Autowired
    private AuthenticationServices authenticationServices;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthorizationRepository<Route> routeRepository;

    @Test
    @DirtiesContext
    @Transactional
    void confirmNew() {

        RegisterRequest registerRequest = new RegisterRequest("testUser", "password", "test@example.com");
        authenticationServices.register(registerRequest);
        User author = usersRepository.findByUsername("testUser").orElseThrow();

        CulturalPOI poi1 = poiServices.createCulturalPOI(0.0, 0.0, author);
        poi1.setName("testPOI1");
        poiServices.confirmNew(poi1);

        CulturalPOI poi2 = poiServices.createCulturalPOI(1.0, 1.0, author);
        poi2.setName("testPOI2");
        poiServices.confirmNew(poi2);

        Set<POI> locations = new HashSet<>();
        locations.add(poi1);
        locations.add(poi2);

        Route route = new Route(author, locations);

        routeServices.confirmNew(route);
        assertTrue(routeRepository.findById(route.getID()).isPresent());
        assertTrue(routeRepository.findById(route.getID()).get().getLocations().contains(poi1));
    }
}