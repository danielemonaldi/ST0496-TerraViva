package it.unicam.cs.ids.TerraViva.Models.Contributes;

public class MultimediaContent extends Content<String> {
    private String caption;

    public MultimediaContent(String author, String data, String caption) {
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
