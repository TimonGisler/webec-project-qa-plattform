package ch.fhnw.webec.frageundantwortapp.pages;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import  ch.fhnw.webec.frageundantwortapp.testutils.QuestionsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * End-to-end test for the Questions page.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionPageE2ETestIT {

    @LocalServerPort
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();

    @Test
    void questions_listsAllQuestions() {
        //after starting application 2 questions are present in database
        var questionsPage = new QuestionsPage(driver, port);
        assertEquals(2, questionsPage.getQuestionList().size());
    }

    @Test
    void questionDetailsLink_redirectsToQuestionDetail() {
        var questionsPage = new QuestionsPage(driver, port);
        //click on a tag of first question
        questionsPage.getQuestionList().getFirst().findElement(By.tagName("a")).click();
        assertEquals("http://localhost:" + port + "/questions/1", driver.getCurrentUrl());
    }
}