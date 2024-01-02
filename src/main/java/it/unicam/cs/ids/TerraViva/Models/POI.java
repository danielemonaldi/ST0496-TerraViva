package it.unicam.cs.ids.TerraViva.Models;

import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@MapLike
@NeedsAuthorization
public class POI implements Contributable{
    private String name;
    private double latitude;
    private double longitude;
    private final Date creation;
    private Date expire;
    private List<Contribute> contents;

    // DA MODIFICARE CON TIPO UTENTE //
    private final String author;

    public POI(String name, double latitude, double longitude, Date creation, @Nullable Date expire, String author) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creation = creation;
        this.expire = expire;
        this.author = author;
        this.contents = new ArrayList<Contribute>();
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

    @Override
    public void post(Contribute item) {
        this.contents.add(item);
    }

    @Override
    public void delete(Contribute item) {
        this.contents.remove(item);
    }
}
