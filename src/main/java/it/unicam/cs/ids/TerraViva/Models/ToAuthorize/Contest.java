package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contents.Content;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

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
    private AuthorizationEntity reference;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> contents;

    public Contest(){}

    public Contest(String name, String theme, String rules, String criteria, Date starting, Date expire, User author) {
        super(author);
        this.name = name;
        this.theme = theme;
        this.rules = rules;
        this.criteria = criteria;
        this.starting = starting;
        this.expire = expire;
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

    public AuthorizationEntity getReference() {
        return reference;
    }

    public void setReference(AuthorizationEntity reference) {
        this.reference = reference;
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public void addContent(Content content) throws Exception {
        this.contents.add(content);
        content.setReference(this);
    }

    public boolean removeContent(Content content) {
        return this.contents.remove(content);
    }
}