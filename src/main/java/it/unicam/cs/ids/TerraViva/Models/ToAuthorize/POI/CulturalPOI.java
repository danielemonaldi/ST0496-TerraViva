package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.EntityType;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;

@Entity
public class CulturalPOI extends POI {
    public CulturalPOI() {}

    public CulturalPOI(double latitude, double longitude, User author) {
        super(latitude, longitude, author);
        this.setType(EntityType.CULTURAL_POI);
    }
}
