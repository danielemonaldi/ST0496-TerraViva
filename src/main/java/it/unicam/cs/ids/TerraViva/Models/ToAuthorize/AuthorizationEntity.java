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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    private User author;

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
