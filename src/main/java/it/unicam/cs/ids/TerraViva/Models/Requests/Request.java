package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.User;

import java.util.Date;

public interface Request {
    long getID();
    String getAuthor();
    Date getCreationDate();
    boolean approved();
    boolean rejected();
    void approve();
    void reject();
}
