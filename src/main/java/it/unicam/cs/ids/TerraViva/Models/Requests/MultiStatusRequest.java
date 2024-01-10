package it.unicam.cs.ids.TerraViva.Models.Requests;

import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MultiStatusRequest implements Request {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long ID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_username", referencedColumnName = "username")
    private User author;

    private Date creation;
    private RequestStatus status = RequestStatus.PENDING;

    public MultiStatusRequest(){}

    public MultiStatusRequest(User author, Date creation) {
        this.author = author;
        this.creation = creation;
    }

    @Override
    public long getID() {
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

    @Override
    public Date getCreationDate() {
        return creation;
    }

    @Override
    public void setCreationDate(Date creation) {
        this.creation = creation;
    }

    @Override
    public boolean approved() {
        return status == RequestStatus.APPROVED;
    }

    @Override
    public boolean rejected() {
        return status == RequestStatus.REJECTED;
    }

    @Override
    public void approve() {
        status = RequestStatus.APPROVED;
    }

    @Override
    public void reject() {
        status = RequestStatus.REJECTED;
    }
}
