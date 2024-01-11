package it.unicam.cs.ids.TerraViva.Models.Requests;


import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "AUTH_REQUEST")
public class AuthorizationRequest extends MultiStatusRequest {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private AuthorizationEntity content;

    public AuthorizationRequest(){}

    public AuthorizationRequest(User author, AuthorizationEntity content, Date creationDate) {
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
