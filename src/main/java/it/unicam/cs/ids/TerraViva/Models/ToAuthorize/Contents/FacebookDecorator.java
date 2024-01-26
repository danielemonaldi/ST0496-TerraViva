package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

public class FacebookDecorator extends SocialDecorator {

    public FacebookDecorator(SocialManager socialManager) {
        super(socialManager);
    }

    @Override
    public void publish(Content content) {}
}