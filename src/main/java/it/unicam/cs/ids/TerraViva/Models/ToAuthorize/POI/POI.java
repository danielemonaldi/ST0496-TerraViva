package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.Content;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public abstract class POI extends AuthorizationEntity {
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Content> contents;

    public POI(){}

    public POI(double latitude, double longitude, User author) {
        super(author);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
