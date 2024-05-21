package ch.fhnw.webec.frageundantwortapp.service;

import ch.fhnw.webec.frageundantwortapp.model.Answer;
import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.model.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
