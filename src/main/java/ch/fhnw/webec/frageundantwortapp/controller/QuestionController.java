package ch.fhnw.webec.frageundantwortapp.controller;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Returns a list of all questions.
     */
    @GetMapping("questions")
    public String questions(Model model) {
        model.addAttribute("questionList", questionService.getAllQuestions());
        return "questions";
    }

    /**
     * Screens for answering a question.
     */
    @GetMapping("questions/{id}")
    public String answerQuestion(@PathVariable int id, Model model) {
        Question question = questionService.getQuestion(id).orElseThrow();
        model.addAttribute("question", question);
        return "answer-question";
    }

    /**
     * Answers a question.
     */
    @PostMapping("questions/{id}")
    public String answerQuestion(@PathVariable int id, String answer, Model model) {
        Question answeredQuestion = questionService.addAnswerToQuestion(id, answer);
        model.addAttribute("question", answeredQuestion);
        return "answer-question";
    }


    @GetMapping("questions/add")
    public String addQuestion() {
        return "add-question";
    }

    /**
     * Adds a new question.
     */
    @PostMapping("questions/add") //TODO TGIS, is Postmapping to just /questions/ maybe more appropriate / Restful?
    public String addQuestion(String title, String text) {
        Question newQuestion = new Question();
        newQuestion.setTitle(title);
        newQuestion.setText(text);

        var savedQuestion = questionService.addQuestion(newQuestion);

        return "redirect:/questions";
    }

}
