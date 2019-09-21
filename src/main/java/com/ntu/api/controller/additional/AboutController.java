package com.ntu.api.controller.additional;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AboutController {

    @FXML
    AnchorPane aboutDlg;
    @FXML
    TextArea aboutTextArea;

    private static String message;

    public static void setMessage(String message) {
        AboutController.message = message;
    }

    @FXML public void initialize() {
            aboutTextArea.setText(message);
    }

    @FXML public void okOnClick(){
        Stage dlg = (Stage)(aboutDlg.getScene().getWindow());
        dlg.close();
//
//        aboutDlg.getParent()
    }
}
