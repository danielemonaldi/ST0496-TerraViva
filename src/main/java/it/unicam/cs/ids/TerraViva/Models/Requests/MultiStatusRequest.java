package it.unicam.cs.ids.TerraViva.Models.Requests;

import java.util.Date;

public class MultiStatusRequest implements Request {
    private final String author;
    private final Date creation;
    private RequestStatus status = RequestStatus.PENDING;

    public MultiStatusRequest(String author, Date creation) {
        this.author = author;
        this.creation = creation;
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
