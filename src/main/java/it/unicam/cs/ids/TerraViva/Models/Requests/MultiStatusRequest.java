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
    private final String author;
    private final Date creation;
    private RequestStatus status = RequestStatus.PENDING;

    public MultiStatusRequest(String author, Date creation) {
        this.author = author;
        this.creation = creation;
    }

    @Override
    public long getID() {
        return ID;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Date getCreationDate() {
        return creation;
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
