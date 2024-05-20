package ch.fhnw.webec.frageundantwortapp.controller;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
