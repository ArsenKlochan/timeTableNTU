package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
import com.ntu.api.model.BoxCleaner;
import com.ntu.api.model.Message;
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

public class addDepartmentController {
    @FXML private AnchorPane addDepartment;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label4;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox box1;
    @FXML private Button button1;
    @FXML private Button button2;

    private static ObservableList<String> facultyList;
    private static ObservableList<String> courseList;

    @FXML public void initialize(){
        label1.setText("Код кафедри");
        label2.setText("Назва кафедри");
        label4.setText("Факультет");
        button1.textProperty().set("Додати кафедру");
        facultyList  = FXCollections.observableArrayList();
        facultyList.addAll(Lists.getFacultyList());
        box1.setEditable(false);
        box1.getItems().setAll(facultyList);
        }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        DepartmentServiceInt departmentService = context.getBean(DepartmentServiceInt.class);
        departmentService.addDepartment(new Department(text1.getText(), text2.getText(),
                Lists.getFacultyService().getFaculties().get(box1.getSelectionModel().getSelectedIndex())));
        clear();
        Message.questionOnClick(addDepartment, "Додавання кафедри", "Додати ще одну кафедру?");
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addDepartment.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        BoxCleaner.boxClear(box1);
    }
}
