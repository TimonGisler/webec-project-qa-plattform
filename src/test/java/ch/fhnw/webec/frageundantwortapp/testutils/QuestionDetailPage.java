package ch.fhnw.webec.frageundantwortapp.testutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.List;

public class QuestionDetailPage {

    private static final String BASE_URL = "http://localhost:";

    public QuestionDetailPage(WebDriver driver, int port, int questionId) {
        driver.get(BASE_URL + port + "/questions/" + questionId);
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".answers__list .answers__item")
    private List<WebElement> answerList;

    public List<WebElement> getAnswerList() {
        return answerList;
    }
}

