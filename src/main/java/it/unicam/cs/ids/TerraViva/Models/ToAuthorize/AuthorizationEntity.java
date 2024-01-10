package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AuthorizationEntity implements AuthorizationSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long ID;
    private boolean authorized;

    public long getID(){
        return ID;
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
