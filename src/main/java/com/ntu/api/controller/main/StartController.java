package com.ntu.api.controller.main;

import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML private AnchorPane start;

    @FXML private void createTimeTableOnClick(){
        Stage create = new Stage();
        create.setTitle("Створення розкладу");
        create.setResizable(false);

        AnchorPane createDlg = null;
        try{
            createDlg = FXMLLoader.load(getClass().getResource("/com/ntu/api/javafx/model/main/timeTableCreater.fxml"));
        }
        catch (IOException e){
            Message.errorCatch(start, "Error", "Create Error");
        }
        create.initOwner(start.getScene().getWindow());
        create.initModality(Modality.WINDOW_MODAL);
        create.setScene(new Scene(createDlg));
        create.show();
    }

    @FXML private void editTimeTableOnClick(){

    }

    @FXML private void showDepartmentListOnClick(){

    }

    @FXML private void showTeacherListOnClick(){

    }

    @FXML private void showGroupeListOnClick(){

    }

    @FXML private void showClassListOnClick(){

    }

    @FXML private void aboutOnClick(){
        String message = "Програма \"NTU TimeTable\" призначена для формування розкладу навчання в Національному транспортному університеті.";
        Message.helpOnClick(start, "About", message);
    }

    @FXML private void helpOnClick(){
        String message = "Алгоритм роботи з програмою:\n" +"- " + "";
        Message. helpOnClick(start,"Help", message);
    }

    @FXML private void closeOnClick(){
        Stage dlg = (Stage)start.getScene().getWindow();
        dlg.close();
    }

    @FXML private void exitOnClick(){
        System.exit(0);
    }

}
