package it.unicam.cs.ids.TerraViva.Models.Requests;


import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class AuthorizationRequest extends MultiStatusRequest {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id", referencedColumnName = "ID")
    private AuthorizationEntity content;

    public AuthorizationRequest(){
        super("", new Date(System.currentTimeMillis()));
        this.content = null;
    }

    public AuthorizationRequest(String author, AuthorizationEntity content, Date creationDate) {
        super(author, creationDate);
        this.content = content;
    }

    public AuthorizationEntity getContent() {
        return content;
    }
    public void setContent(AuthorizationEntity content) {
        this.content = content;
    }
}
