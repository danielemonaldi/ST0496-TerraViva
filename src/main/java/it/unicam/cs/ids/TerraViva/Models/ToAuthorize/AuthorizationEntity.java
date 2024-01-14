package it.unicam.cs.ids.TerraViva.Models.ToAuthorize;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.MultimediaContent;
import it.unicam.cs.ids.TerraViva.Models.ToAuthorize.Contributes.TextualContent;
import it.unicam.cs.ids.TerraViva.Models.User;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = POI.class, name = "POI"),
        @JsonSubTypes.Type(value = TextualContent.class, name = "TEXTUAL_CONTENT"),
        @JsonSubTypes.Type(value = MultimediaContent.class, name = "MULTIMEDIA_CONTENT")})
public abstract class AuthorizationEntity implements AuthorizationSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long ID;

    private boolean authorized;

    @Enumerated(EnumType.STRING)
    private EntityType type;

    @ManyToOne
    @JoinColumn(name = "author_username")
    private User author;

    public AuthorizationEntity(){}

    public AuthorizationEntity(User author) {
        this.author = author;
        this.authorized = false;
    }

    public long getID(){
        return ID;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(User author) {
        this.author = author;
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

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
