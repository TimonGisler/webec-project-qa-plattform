package ch.fhnw.webec.frageundantwortapp.pages;

import ch.fhnw.webec.frageundantwortapp.testutils.QuestionDetailPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;


/**
 * End-to-end test for the Questions page.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuestionDetailPageE2ETestIT {

    @LocalServerPort
    int port;

    HtmlUnitDriver driver = new HtmlUnitDriver();

    @Test
    void deleteAnswerLink_deletesAnswer() {
        QuestionDetailPage questionDetailPage = new QuestionDetailPage(driver, port, 1);
        var answerList = questionDetailPage.getAnswerList();
        int answerCount = answerList.size();
        answerList.getFirst().findElement(By.tagName("a")).click();

        assertEquals(answerCount - 1, questionDetailPage.getAnswerList().size());
    }
}