package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class PromotionRequest extends MultiStatusRequest {
    private final Role requestedRole;
    private final String motivation;

    public PromotionRequest(String author, String motivation, Role requestedRole, Date creationDate) {
        super(author, creationDate);
        this.requestedRole = requestedRole;
        this.motivation = motivation;
    }

    public Role getRequestedRole() {
        return requestedRole;
    }

    public String getMotivation() {
        return motivation;
    }
}
