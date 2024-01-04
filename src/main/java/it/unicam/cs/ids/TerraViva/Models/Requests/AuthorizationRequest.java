package it.unicam.cs.ids.TerraViva.Models.Requests;


import it.unicam.cs.ids.TerraViva.Models.AuthorizationSubject;

import java.util.Date;

public class AuthorizationRequest extends MultiStatusRequest {
    private final AuthorizationSubject content;

    public AuthorizationRequest(String author, AuthorizationSubject content, Date creationDate) {
        super(author, creationDate);
        this.content = content;
    }

    public AuthorizationSubject getContent() {
        return content;
    }


}
