package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

public class XDecorator extends SocialDecorator {

    public XDecorator(SocialManager socialManager) {
        super(socialManager);
    }

    @Override
    public void publish(Content content) {}
}