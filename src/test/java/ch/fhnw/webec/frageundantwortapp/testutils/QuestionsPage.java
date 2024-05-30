package ch.fhnw.webec.frageundantwortapp.testutils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

public class QuestionsPage {

    private static final String BASE_URL = "http://localhost:";

    public QuestionsPage(WebDriver driver, int port) {
        driver.get(BASE_URL + port + "/questions");
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "question-list__item")
    private List<WebElement> questionList;

    public List<WebElement> getQuestionList() {
        return questionList;
    }
}

