package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;


    public QuestionService(QuestionRepository repository, TagRepository tagRepository) {
        this.questionRepository = repository;
        this.tagRepository = tagRepository;
    }

    public Question addQuestion(Question questionToAdd) {
        return questionRepository.save(questionToAdd);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }


    public Optional<Question> getQuestion(int id) {
        return questionRepository.findById(id);
    }

    public Question addAnswerToQuestion(int id, String answer) {
        Question question = questionRepository.findById(id).orElseThrow();
        Answer answerToAdd = new Answer();
        answerToAdd.setText(answer);

        question.addAnswer(answerToAdd);
        return questionRepository.save(question);
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void updateQuestion(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    public void deleteAnswer(int questionId, int answerId) {
        Question question = questionRepository.findById(questionId).orElseThrow();
        question.getAnswers().removeIf(answer -> answer.getId() == answerId);
        questionRepository.save(question);
    }
}
