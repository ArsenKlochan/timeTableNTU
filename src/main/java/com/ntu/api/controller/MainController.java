package com.ntu.api.controller;

import com.ntu.api.model.Message;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MainController {
    @FXML
    AnchorPane main;

    public void enterOnClick(){
        Stage authentication = new Stage();
        authentication.setTitle("Вхід до системи");
        authentication.setResizable(false);

        AnchorPane dlgAuthentication= null;

        try {
            dlgAuthentication = FXMLLoader.load(getClass().getResource("/com/ntu/api/javafx/model/additional/authentification.fxml"));
        } catch (IOException e) {
            Message.errorCatch(main, "Error", "Authentication Error");
        }

        authentication.initOwner(main.getScene().getWindow());
        authentication.initModality(Modality.WINDOW_MODAL);

        authentication.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Stage dlg = (Stage)(main.getScene().getWindow());
                dlg.close();
            }
        });
        cancelOnClick();
        authentication.setScene(new Scene(dlgAuthentication));
        authentication.show();
    }

    public void registrationOnClick(){
        Stage registration = new Stage();
        registration.setTitle("Реєстрація нового користувача");
        registration.setResizable(false);

        AnchorPane regDlg = null;
        try {
            regDlg = FXMLLoader.load(getClass().getResource("/com/ntu/api/javafx/model/additional/registration.fxml"));
        } catch (IOException e) {
            Message.errorCatch(main, "Error","Registration Error");
        }

        registration.initOwner(main.getScene().getWindow());
        registration.initModality(Modality.WINDOW_MODAL);
        registration.setScene(new Scene(regDlg));
        registration.show();
        cancelOnClick();
    }

    public void cancelOnClick(){
        Stage dlg = (Stage) main.getScene().getWindow();
        dlg.close();
    }

    public void closeOnClick(){
        System.exit(0);
    }

    public void helpOnClick(){
        String message = "Алгоритм роботи з програмою:\n" +"- " + "";
        Message.helpOnClick(main,"Help", message);
    }

    public void aboutOnClick(){
        String message = "Програма \"NTU TimeTable\" призначена для формування розкладу навчання в Національному транспортному університеті.";
        Message.helpOnClick(main,"About", message);
    }
}


