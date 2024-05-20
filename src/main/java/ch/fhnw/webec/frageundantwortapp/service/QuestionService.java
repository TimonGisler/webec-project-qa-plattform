package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.model.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    public Question addQuestion(Question questionToAdd) {
        return repository.save(questionToAdd);
    }

    public List<Question> getAllQuestions() {
        return repository.findAll();
    }
}
