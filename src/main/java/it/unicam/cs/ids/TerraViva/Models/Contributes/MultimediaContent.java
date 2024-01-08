package it.unicam.cs.ids.TerraViva.Models.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;

public class MultimediaContent extends Content<String> {
    private String caption;

    public MultimediaContent(User author, String data, String caption) {
        super(author, data);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
