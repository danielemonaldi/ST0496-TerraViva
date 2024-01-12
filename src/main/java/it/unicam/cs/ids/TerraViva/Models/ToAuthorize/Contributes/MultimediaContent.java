package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MultimediaContent")
public class MultimediaContent extends Content<String> {
    private String caption;

    public MultimediaContent(User author, String data, String caption) {
        super(author, data);
        this.caption = caption;
    }

    public MultimediaContent() {}

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
