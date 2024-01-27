package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Route extends AuthorizationEntity {
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "route_member",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "poi_ID")})
    private Set<POI> locations;

    public Route() {
        this.setType(EntityType.ROUTE);
        this.locations = new HashSet<>();
    }

    public Route(User author, Set<POI> locations) {
        super(author);
        this.locations = locations;
        this.setType(EntityType.ROUTE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<POI> getLocations() {
        return locations;
    }

    public void setLocations(List<POI> locations) throws Exception {
        Set<POI> tempLocations = this.locations;
        try {
            this.locations.clear();
            for (POI location : locations) {
                this.addLocation(location);
            }
        } catch (Exception e) {
            this.locations = tempLocations;
            throw e;
        }
    }

    public void addLocation(POI location) throws Exception {
        this.locations.add(location);
        location.getRoutes().add(this);
    }

    public boolean removeLocation(POI location) {
        location.getRoutes().remove(this);
        return this.locations.remove(location);
    }
}
