package it.unicam.cs.ids.TerraViva.Models.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;

public interface Contribute<T> {
    public User getAuthor();
    public T getData();
}
