package it.unicam.cs.ids.TerraViva.Models;

public class AuthorizationRequest extends Request{
    private final NeedsAuthorization content;

    protected AuthorizationRequest(String author, String motivation, NeedsAuthorization content) {
        super(author, motivation);
        this.content = content;
    }

    public NeedsAuthorization getContent() {
        return content;
    }
}
