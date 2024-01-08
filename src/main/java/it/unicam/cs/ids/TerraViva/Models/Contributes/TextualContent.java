package it.unicam.cs.ids.TerraViva.Models.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;

public class TextualContent extends Content<String>{
    public TextualContent(User author, String data) {
        super(author, data);
    }
}
