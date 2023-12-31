package it.unicam.cs.ids.TerraViva.Models;

public interface Contributable {
    public void post(Contribute item);
    public void delete(Contribute item);
}
