package ch.fhnw.webec.frageundantwortapp.controller;

import ch.fhnw.webec.frageundantwortapp.model.Question;
import ch.fhnw.webec.frageundantwortapp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    private final QuestionService questionServiceMock = mock(QuestionService.class);
    private final QuestionController sut = new QuestionController(questionServiceMock);



    @Test
    void updateQuestion_ifTitleIsMissingNoQuestionGetsSaved() {
        Model model = mock(Model.class);
        int questionId = 1;
        when(questionServiceMock.getQuestion(questionId)).thenReturn(Optional.of(new Question()));
        sut.updateQuestion(questionId, null, "text", new int[]{1}, model);

        //verify that no question was saved (questionServiceMock.addQuestion was never called)
        verify(questionServiceMock, times(0)).updateQuestion(any());
    }

    @Test
    void updateQuestion_ifTextIsMissingNoQuestionGetsSaved() {
        Model model = mock(Model.class);
        int questionId = 1;
        when(questionServiceMock.getQuestion(questionId)).thenReturn(Optional.of(new Question()));
        sut.updateQuestion(questionId, "title", null, new int[]{1}, model);

        //verify that no question was saved (questionServiceMock.addQuestion was never called)
        verify(questionServiceMock, times(0)).updateQuestion(any());
    }

    @Test
    void updateQuestion_ifTagsAreMissingNoQuestionGetsSaved() {
        Model model = mock(Model.class);
        int questionId = 1;
        when(questionServiceMock.getQuestion(questionId)).thenReturn(Optional.of(new Question()));
        sut.updateQuestion(questionId, "title", "text", null, model);

        //verify that no question was saved (questionServiceMock.addQuestion was never called)
        verify(questionServiceMock, times(0)).updateQuestion(any());
    }

    @Test
    void updateQuestion_ifInputIsInvalidNoRedirectHappens() {
        Model model = mock(Model.class);
        int questionId = 1;
        when(questionServiceMock.getQuestion(questionId)).thenReturn(Optional.of(new Question()));
        String viewName = sut.updateQuestion(questionId, null, null, null, model);

        //assert that no redirect happened
        assertEquals("update-question", viewName);
    }

    @Test
    void updateQuestion_ifInputIsInvalidErrorAttributeGetsSet() {
        Model model = mock(Model.class);
        int questionId = 1;
        when(questionServiceMock.getQuestion(questionId)).thenReturn(Optional.of(new Question()));
        String viewName = sut.updateQuestion(questionId, null, null, null, model);

        //assert that error attribute was set
        verify(model).addAttribute(eq("error"), anyString());
    }
}