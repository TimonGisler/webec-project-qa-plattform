package ch.fhnw.webec.frageundantwortapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    //TODO TGIS, i dont see any reason to add a list of questions on the tag side, figure out if this is needed for full points

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String tag) {
        this.name = tag;
    }
}
