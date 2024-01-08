package it.unicam.cs.ids.TerraViva.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MapLike
public class POI implements Contributable, AuthorizationSubject {
    private String name;
    private double latitude;
    private double longitude;
    private final Date creation;
    private Date expire;
    private boolean authorized;

    private final List<Contribute> contents;

    private final User author;

    public POI(String name, double latitude, double longitude, Date creation, @Nullable Date expire, User author) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creation = creation;
        this.expire = expire;
        this.author = author;
        this.contents = new ArrayList<Contribute>();
        this.authorized = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Date getCreation() {
        return creation;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public User getAuthor() {
        return author;
    }

    public List<Contribute> getContents() {
        return contents;
    }

    public boolean authorized() { return authorized; }

    @Override
    public void post(Contribute item) {
        this.contents.add(item);
    }

    @Override
    public void delete(Contribute item) {
        this.contents.remove(item);
    }

    @Override
    public void authorize() {
        this.authorized = true;
    }

    @Override
    public void deny() {
        this.authorized = false;
    }
}
