package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public abstract class Content extends AuthorizationEntity implements Contribute {
    private String data;

    public Content(User author, String data) {
        super(author);
        this.data = data;
    }

    public Content() {}

    @Override
    public String getData() {
        return data;
    }

    @Override
    public void setData(String data) {
        this.data = data;
    }
}
