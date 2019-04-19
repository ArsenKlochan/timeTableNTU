package com.ntu.api.controller.additional;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class QuestionController {
    @FXML private AnchorPane questionPane;
    @FXML private TextArea fldQuestion;
    private static String question;

    public static void setQuestion(String question) {
        QuestionController.question = question;
    }

    @FXML private void initialize(){
        fldQuestion.setText(question);
    }
    @FXML private void yesOnClick(){
        Stage dlg = (Stage)(questionPane.getScene().getWindow());
        dlg.close();
    }
    @FXML private void noOnClick(){
        Stage dlg = (Stage)(questionPane.getScene().getWindow());
        dlg.getOnCloseRequest().handle(new WindowEvent(dlg,WindowEvent.WINDOW_CLOSE_REQUEST));
        dlg.close();
    }


}
