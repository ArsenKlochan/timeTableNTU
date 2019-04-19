package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
import com.ntu.api.model.BoxCleaner;
import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class addFacultyController {
    @FXML private AnchorPane addFaculty;
    @FXML private Label label1;
    @FXML private TextField text1;
    @FXML private Button button1;
    @FXML private Button button2;

    @FXML public void initialize(){
        label1.setText("Назва факультету");
        button1.textProperty().set("Додати факультет");
    }
    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);
        facultyService.addFaculty(new Faculty(text1.getText()));
        clear();
        Message.questionOnClick(addFaculty, "Додавання факультету", "Додати ще один факультет?");
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addFaculty.getScene().getWindow());
        dlg.close();
    }
    private void clear(){
        text1.clear();
    }
}
