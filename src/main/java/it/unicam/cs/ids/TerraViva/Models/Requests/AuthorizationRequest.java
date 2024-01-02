package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.NeedsAuthorization;

import java.util.Date;

public class AuthorizationRequest extends MultiStatusRequest {
    private final NeedsAuthorization content;

    public AuthorizationRequest(String author, NeedsAuthorization content, Date creationDate) {
        super(author, creationDate);
        this.content = content;
    }

    public NeedsAuthorization getContent() {
        return content;
    }


}
