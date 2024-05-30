package ch.fhnw.webec.frageundantwortapp.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
public class CustomErrorController implements ErrorController {

    private final String questionPattern = "^/questions/\\d+$";
    private final String answerPattern = "^/questions/\\d+/answers/\\d+$";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status == null) {
            return "error";
        }


        int statusCode = Integer.parseInt(status.toString());
        String url = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        if (statusCode == HttpStatus.NOT_FOUND.value() && url.matches(questionPattern)) {
            model.addAttribute("questionId", url.substring(url.lastIndexOf("/") + 1));
            return "question-error-page";
        } else {
            model.addAttribute("url", url);
            model.addAttribute("statusCode", statusCode);
            return "general-error-page";
        }
    }
}
