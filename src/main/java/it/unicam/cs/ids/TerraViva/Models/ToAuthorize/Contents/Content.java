package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
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

    public void setReference(AuthorizationEntity reference) throws Exception {
        if(reference instanceof POI || reference instanceof Contest) {
            this.reference = reference;
        } else {
            throw new Exception("Invalid type for Content reference.");
        }
    }
}
