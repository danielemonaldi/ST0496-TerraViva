package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.POI.POI;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTEST")
public class Contest extends AuthorizationEntity {

    private String name;
    private String theme;
    private String rules;
    private String criteria;

    private Date starting;
    private Date expire;

    @ManyToOne
    private POI reference;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents;

    public Contest() { contents = new ArrayList<>(); }

    public Contest(User author, POI reference) {
        super(author);
        this.reference = reference;
        contents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Date getStarting() {
        return starting;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public POI getReference() {
        return reference;
    }

    public void setReference(POI reference) {
        this.reference = reference;
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

    public void addContent(Content content) throws Exception {
        this.contents.add(content);
        content.setReference(this);
    }

    public boolean removeContent(Content content) {
        return this.contents.remove(content);
    }
}