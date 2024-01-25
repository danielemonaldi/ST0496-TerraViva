package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.EntityType;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;

@Entity
public class CommercialPOI extends POI {
    private String openingTime;

    public CommercialPOI() {}

    public CommercialPOI(double latitude, double longitude, User author) {
        super(latitude, longitude, author);
        this.setType(EntityType.COMMERCIAL_POI);
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }
}
