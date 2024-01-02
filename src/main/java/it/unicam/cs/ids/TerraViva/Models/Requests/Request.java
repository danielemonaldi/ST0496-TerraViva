package it.unicam.cs.ids.TerraViva.Models.Requests;

import java.util.Date;

public interface Request {
    String getAuthor();
    Date getCreationDate();
    boolean approved();
    boolean rejected();
    void approve();
    void reject();
}
