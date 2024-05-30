package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the {@link QuestionService}.
 */
@DataJpaTest
class QuestionServiceTest {

    private final QuestionService sut;
    private final QuestionRepository repo;
    private final AnswerRepository answerRepository;

    @Autowired
    QuestionServiceTest(QuestionRepository questionRepository, TagRepository tagRepository, AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
        this.sut = new QuestionService(questionRepository, tagRepository);
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

    @Test
    void deleteQuestion_deletesQuestionFromDb() {
        var question = new Question();
        question.setTitle("Test Title");
        question.setText("Test Details");
        var savedQuestion = repo.save(question);

        sut.deleteQuestion(savedQuestion.getId());

        assertFalse(repo.existsById(savedQuestion.getId()));
    }

    @Test
    void addAnswerToQuestion_savesAnswerWithCorrectDetails() {
        var question = new Question();
        question.setTitle("Test Title");
        question.setText("Test Details");
        var savedQuestion = repo.save(question);

        String answerText = "Test Answer";
        var savedQuestionWithAnswer = sut.addAnswerToQuestion(savedQuestion.getId(), answerText);

        assertNotNull(savedQuestionWithAnswer);
        assertNotNull(savedQuestionWithAnswer.getAnswers());
        assertEquals(1, savedQuestionWithAnswer.getAnswers().size());
        assertEquals(answerText, savedQuestionWithAnswer.getAnswers().getFirst().getText());
    }

    @Test
    void deleteQuestion_deletesAssociatedAnswersFromDb() {
        var question = new Question();
        question.setTitle("Test Title");
        question.setText("Test Details");

        var answerOfQuestion = new Answer();
        answerOfQuestion.setText("Test Answer");
        question.addAnswer(answerOfQuestion);
        var savedQuestion = repo.save(question);

        var savedAnswerId = savedQuestion.getAnswers().getFirst().getId();

        sut.deleteQuestion(savedQuestion.getId());

        assertFalse(answerRepository.existsById(savedAnswerId));
    }
}