package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.EntityType;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class EventPOI extends POI {
    private Date creation;
    private Date expire;

    public EventPOI() {}

    public EventPOI(double latitude, double longitude, User author) {
        super(latitude, longitude, author);
        this.setType(EntityType.EVENT_POI);
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }
}
