package it.unicam.cs.ids.TerraViva.Models;

public class PromotionRequest extends Request{
    private final String requestedRole;

    public PromotionRequest(String author, String motivation, String requestedRole) {
        super(author, motivation);
        this.requestedRole = requestedRole;
    }

    public String getRequestedRole() {
        return requestedRole;
    }
}
