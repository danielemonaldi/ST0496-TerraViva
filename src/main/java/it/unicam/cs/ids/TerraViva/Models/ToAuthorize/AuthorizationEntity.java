package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AuthorizationEntity implements AuthorizationSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long ID;

    private boolean authorized;

    @ManyToOne
    @JoinColumn(name = "author_username")
    private User author;

    public AuthorizationEntity(){}

    public AuthorizationEntity(User author) {
        this.author = author;
        this.authorized = false;
    }

    public long getID(){
        return ID;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean authorized() { return authorized; }

    @Override
    public void authorize() {
        this.authorized = true;
    }

    @Override
    public void deny() {
        this.authorized = false;
    }
}
