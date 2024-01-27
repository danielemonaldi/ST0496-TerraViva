package it.unicam.cs.ids.TerraViva.Services;

import it.unicam.cs.ids.TerraViva.Models.Requests.AuthorizationRequest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.CulturalPOI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Route;
import it.unicam.cs.ids.TerraViva.Models.User;
import it.unicam.cs.ids.TerraViva.Repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RouteServices {
    @Autowired
    private AuthorizationRepository<Route> routeRepository;

    @Autowired
    private AuthorizationRepository<CulturalPOI> poiRepository;

    @Autowired
    private RequestServices requestServices;

    public Route createRoute(User author, Set<POI> locations) {
        return new Route(author, locations);
    }

    public void delete(Route route) {
        routeRepository.delete(route);
    }

    public List<Route> getRoutes() { return routeRepository.findAll(); }

    public Optional<Route> getRoutes(long ID) {
        return routeRepository.findById(ID);
    }

    public void confirmNew(Route route) {
        // if(!poi.getAuthor().getAuthorities().contains(Role.AUTHORIZED_ENTERTAINER) &&
        //        !poi.getAuthor().getAuthorities().contains(Role.AUTHORIZED_CONTRIBUTOR) &&
        //        !poi.authorized()) {

        route.getLocations().forEach(l -> System.out.println(poiRepository.findById(l.getID())));
            Date creation = new Date(System.currentTimeMillis());
            AuthorizationRequest request = new AuthorizationRequest(route.getAuthor(), route, creation);
            requestServices.submit(request);
        // } else {
        //    poi.authorize();
        // }
        routeRepository.save(route);
    }
    public void save(Route route) { routeRepository.save(route); }
}
