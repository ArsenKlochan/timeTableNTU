package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
import com.ntu.api.model.BoxCleaner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class editRemoveFacultyController {
    @FXML private AnchorPane removeDeleteFaculty;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveFacultyController.bool = bool;
    }

    private static ObservableList<String> objectsList;
    Faculty faculty;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);

    @FXML public void initialize(){
        objectsList = FXCollections.observableArrayList();
        label0.setText("Факультет");
        label1.setText("Назва факультету");
        if (bool){
            button1.textProperty().set("Зберегти факультет");
        }
        else{
            button1.textProperty().set("Видалити факультет");
            text1.setDisable(true);
        }
        objectsList.addAll(Lists.getFacultyList());
        box.setEditable(false);
        box.getItems().setAll(objectsList);
    }

    @FXML public void okOnClick(){
        if(bool){
            faculty.setFacultyName(text1.getText());
            facultyService.updateFaculty(faculty);
        }
        else{
            facultyService.deleteFacultu(faculty);
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) removeDeleteFaculty.getScene().getWindow();
        dlg.close();
    }

    @FXML public void choseOnClick(){
        List<String> parameters = new ArrayList<>();
        faculty = facultyService.getFaculties().get(box.getSelectionModel().getSelectedIndex());
        parameters = facultyService.getParametersInString(faculty);
        text1.setText(parameters.get(0));
    }

}
