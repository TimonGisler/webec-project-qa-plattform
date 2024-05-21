package ch.fhnw.webec.frageundantwortapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer id;

    private String title; // TODO TGIS, consider making this non nullable
    private String text; // TODO TGIS, consider making this non nullable
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

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

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer answerToAdd) {
        answers.add(answerToAdd);
    }
}
