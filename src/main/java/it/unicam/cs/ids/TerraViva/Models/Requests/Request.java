package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.User;

import java.util.Date;

public interface Request {
    long getID();
    User getAuthor();
    void setAuthor(User author);
    Date getCreationDate();
    void setCreationDate(Date creation);
    boolean approved();
    boolean rejected();
    void approve();
    void reject();
}
