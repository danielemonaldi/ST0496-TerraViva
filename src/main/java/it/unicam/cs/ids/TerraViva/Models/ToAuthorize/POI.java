package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POI")
public class POI extends AuthorizationEntity {
    private String name;
    private double latitude;
    private double longitude;
    private Date creation;
    private Date expire;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Contest> contests;

    public POI(){}

    public POI(String name, double latitude, double longitude, Date creation, @Nullable Date expire, User author) {
        super(author);
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.creation = creation;
        this.expire = expire;
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

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }
}