package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class Content extends AuthorizationEntity {
    private String data;

    @ManyToOne
    private AuthorizationEntity reference;

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

    public AuthorizationEntity getReference() {
        return reference;
    }

    public void setReference(AuthorizationEntity reference) {
        /*
        if(reference instanceof POI || reference instanceof Contest) {

        } else {

        }*/
        this.reference = reference;
    }
}
