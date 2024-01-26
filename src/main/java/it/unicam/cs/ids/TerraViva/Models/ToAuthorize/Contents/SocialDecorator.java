package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

public abstract class SocialDecorator implements SocialManager {

    private final SocialManager wrappee;

    public SocialDecorator(SocialManager socialManager) {
        this.wrappee = socialManager;
    }

    @Override
    public abstract void publish(Content content);
}