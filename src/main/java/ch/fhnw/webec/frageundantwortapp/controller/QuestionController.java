package ch.fhnw.webec.frageundantwortapp.controller;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.model.Tag;
import ch.fhnw.webec.frageundantwortapp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.HTML;
import java.util.List;

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
     * Detail Screen of a question.
     * Here the title, text, questions, can be seen and updated.
     */
    @GetMapping("questions/{id}")
    public String answerQuestion(@PathVariable int id, Model model) {
        Question question = questionService.getQuestion(id).orElseThrow();
        model.addAttribute("question", question);
        return "question-detail";
    }

    @GetMapping("questions/{id}/update")
    public String updateQuestion(@PathVariable int id, Model model) {
        Question question = questionService.getQuestion(id).orElseThrow();
        model.addAttribute("question", question);
        model.addAttribute("tagList", questionService.getAllTags());
        return "update-question";
    }

    @PostMapping("questions/{id}/update")
    public String updateQuestion(@PathVariable int id, String title, String text, int[] tags) {
        Question question = questionService.getQuestion(id).orElseThrow();
        question.setTitle(title);
        question.setText(text);
        question.getTags().clear();
        for (int tagId : tags) {
            question.addTag(questionService.getAllTags().stream().filter(tag -> tag.getId() == tagId).findFirst().orElseThrow());
        }
        questionService.updateQuestion(question);
        return "redirect:/questions/" + id;
    }

    //TODO TGIS, idk getmapping for that seems weird
    @GetMapping("questions/{id}/delete")
    public String deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return "redirect:/questions";
    }

    /**
     * Answers a question.
     */
    @PostMapping("questions/{id}")
    public String answerQuestion(@PathVariable int id, String answer, Model model) {
        Question answeredQuestion = questionService.addAnswerToQuestion(id, answer);
        model.addAttribute("question", answeredQuestion);
        return "question-detail";
    }


    @GetMapping("questions/add")
    public String addQuestion(Model model) {
        model.addAttribute("tagList",  questionService.getAllTags());
        return "add-question";
    }

    /**
     * Adds a new question.
     */
    @PostMapping("questions/add") //TODO TGIS, is Postmapping to just /questions/ maybe more appropriate / Restful?
    public String addQuestion(String title, String text, int[] tags) {
        Question newQuestion = new Question();
        newQuestion.setTitle(title);
        newQuestion.setText(text);

        //TODO TGIS, this is probably not the cleanest : ), getting all tags and then filtering : ))). --> maybe move entire logic into questinServie
        for (int tagId : tags) {
            newQuestion.addTag(questionService.getAllTags().stream().filter(tag -> tag.getId() == tagId).findFirst().orElseThrow());
        }

        var savedQuestion = questionService.addQuestion(newQuestion);

        return "redirect:/questions";
    }

}
