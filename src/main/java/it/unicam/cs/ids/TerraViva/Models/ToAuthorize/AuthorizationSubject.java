package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;


import it.unicam.cs.ids.TerraViva.Models.User;

public interface AuthorizationSubject {
    public User getAuthor();
    public void setAuthor(User author);
    public void authorize();
    public void deny();
}
