package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.User;

import java.util.Date;

public class PromotionRequest extends MultiStatusRequest {
    private final String requestedRole;
    private final String motivation;

    public PromotionRequest(User author, String motivation, String requestedRole, Date creationDate) {
        super(author, creationDate);
        this.requestedRole = requestedRole;
        this.motivation = motivation;
    }

    public String getRequestedRole() {
        return requestedRole;
    }

    public String getMotivation() {
        return motivation;
    }
}
