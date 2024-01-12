package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "TextualContent")
public class TextualContent extends Content<String>{
    public TextualContent(User author, String data) {
        super(author, data);
    }

    public TextualContent() {}
}
