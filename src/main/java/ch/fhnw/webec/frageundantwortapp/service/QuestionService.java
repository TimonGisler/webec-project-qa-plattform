package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository repository;
    private final TagRepository tagRepository;


    public QuestionService(QuestionRepository repository, TagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    public Question addQuestion(Question questionToAdd) {
        return repository.save(questionToAdd);
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }


    public Optional<Question> getQuestion(int id) {
        return repository.findById(id);
    }

    public Question addAnswerToQuestion(int id, String answer) {
        Question question = repository.findById(id).orElseThrow();
        Answer answerToAdd = new Answer();
        answerToAdd.setText(answer);

        question.addAnswer(answerToAdd);
        return repository.save(question);
    }

    //TODO TGIS, is this the correct place for this method? Or do I need a new service?
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public void updateQuestion(Question question) {
        repository.save(question);
    }

    public void deleteQuestion(int id) {
        repository.deleteById(id);
    }

    public void deleteAnswer(int questionId, int answerId) {
        Question question = repository.findById(questionId).orElseThrow();
        question.getAnswers().removeIf(answer -> answer.getId() == answerId);
        repository.save(question);
    }
}
