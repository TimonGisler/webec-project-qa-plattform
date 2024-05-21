package ch.fhnw.webec.frageundantwortapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Answer {

        @Id
        @GeneratedValue
        private Integer id;

        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }


}
