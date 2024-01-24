package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;

@Entity
public abstract class Content extends AuthorizationEntity {
    private String data;

    public Content(User author, String data) {
        super(author);
        this.data = data;
    }

    public Content() {}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
