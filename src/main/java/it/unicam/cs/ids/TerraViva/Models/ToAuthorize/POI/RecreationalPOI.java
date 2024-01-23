package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.EntityType;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class RecreationalPOI extends POI {
    private String openingTime;

    private List<String> activities;

    public RecreationalPOI() {}

    public RecreationalPOI(double latitude, double longitude, User author) {
        super(latitude, longitude, author);
        this.setType(EntityType.RECREATIONAL_POI);
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}
