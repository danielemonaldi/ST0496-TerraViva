package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.Role;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "PROM_REQUEST")
public class PromotionRequest extends MultiStatusRequest {
    private Role requestedRole;
    private String motivation;

    public PromotionRequest(){}

    public PromotionRequest(User author, String motivation, Role requestedRole, Date creationDate) {
        super(author, creationDate);
        this.requestedRole = requestedRole;
        this.motivation = motivation;
    }

    public Role getRequestedRole() {
        return requestedRole;
    }

    public void setRequestedRole(Role requestedRole) {
        this.requestedRole = requestedRole;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}
