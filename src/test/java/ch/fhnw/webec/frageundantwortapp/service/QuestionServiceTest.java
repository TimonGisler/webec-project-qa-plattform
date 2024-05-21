package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.model.QuestionRepository;
import ch.fhnw.webec.frageundantwortapp.model.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the {@link QuestionService}.
 */
@DataJpaTest
class QuestionServiceTest {

    QuestionService sut;
    QuestionRepository repo;

    @Autowired
    QuestionServiceTest(QuestionRepository questionRepository, TagRepository tagRepository) {
        sut = new QuestionService(questionRepository, tagRepository);
        this.repo = questionRepository;
    }

    @Test
    void addQuestion_savesQuestionWithCorrectDetails() {
        var question = new Question();
        String titleOfQuestion = "Test Title";
        question.setTitle(titleOfQuestion);
        String detailOfQuestion = "Test Details";
        question.setText(detailOfQuestion);

        var savedQuestion = sut.addQuestion(question);

        assertNotNull(savedQuestion);
        assertNotNull(savedQuestion.getId());
        assertEquals(titleOfQuestion, savedQuestion.getTitle());
        assertEquals(detailOfQuestion, savedQuestion.getText());
    }

}