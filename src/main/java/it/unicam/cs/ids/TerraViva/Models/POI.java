package it.unicam.cs.ids.TerraViva.Models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MapLike
@Entity
@Table(name = "POI")
public class POI implements AuthorizationSubject {
    @Id
    private String name;
    private double latitude;
    private double longitude;
    private Date creation;
    private Date expire;
    private boolean authorized;
    private String author;

    public POI(){}

    public POI(String name, double latitude, double longitude, Date creation, @Nullable Date expire, String author) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creation = creation;
        this.expire = expire;
        this.author = author;
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

    public String getAuthor() {
        return author;
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
