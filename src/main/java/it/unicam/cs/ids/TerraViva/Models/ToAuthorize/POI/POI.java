package it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.AuthorizationEntity;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contest;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public abstract class POI extends AuthorizationEntity {
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contest> contests;

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

    public void setContents(List<Content> contents) throws Exception {
        List<Content> tempContents = this.contents;
        try {
            this.contents.clear();
            for (Content content : contents) {
                this.addContent(content);
            }
        } catch (Exception e) {
            this.contents = tempContents;
            throw e;
        }
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) throws Exception {
        List<Contest> tempContests = this.contests;
        try {
            this.contests.clear();
            for (Contest contest : contests) {
                this.addContest(contest);
            }
        } catch (Exception e) {
            this.contests = tempContests;
            throw e;
        }
    }

    public void addContent(Content content) throws Exception {
        this.contents.add(content);
        content.setReference(this);
    }

    public boolean removeContent(Content content) {
        return this.contents.remove(content);
    }

    public void addContest(Contest contest) throws Exception {
        this.contests.add(contest);
        contest.setReference(this);
    }

    public boolean removeContest(Contest contest) {
        return this.contests.remove(contest);
    }
}
