package ch.fhnw.webec.frageundantwortapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    private String title; // TODO TGIS, consider making this non nullable
    private String text; // TODO TGIS, consider making this non nullable


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String details) {
        this.text = details;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
