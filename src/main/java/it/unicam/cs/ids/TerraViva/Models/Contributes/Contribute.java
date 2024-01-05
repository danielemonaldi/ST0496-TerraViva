package it.unicam.cs.ids.TerraViva.Models.Contributes;

public interface Contribute<T> {
    public String getAuthor();
    public T getData();
}
