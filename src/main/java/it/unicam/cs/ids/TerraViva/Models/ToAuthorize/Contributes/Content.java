package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public abstract class Content extends AuthorizationEntity implements Contribute {
    private String data;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reference_id")
    private AuthorizationEntity refersTo;

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

    @Override
    public AuthorizationEntity getRefersTo() {
        return refersTo;
    }

    @Override
    public void setRefersTo(AuthorizationEntity refersTo) {
        this.refersTo = refersTo;
    }
}
