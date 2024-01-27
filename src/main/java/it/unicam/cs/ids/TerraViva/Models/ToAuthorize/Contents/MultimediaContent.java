package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MultimediaContent")
public class MultimediaContent extends Content {
    private String caption;

    public MultimediaContent(User author, String data) {
        super(author, data);
    }

    public MultimediaContent() {}

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
