package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.service.serviceInterface.TeacherServiceInt;
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

public class addTeacherController {
    @FXML private AnchorPane addTeacher;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static ObservableList<String> departmentList;
    private static ObservableList<String> positionList;

    @FXML public void initialize(){
        label1.setText("Прізвище");
        label2.setText("Ім'я Побатькові");
        label3.setText("Кафедра");
        label4.setText("Посада");
        button1.textProperty().set("Додати викладача");
        departmentList = FXCollections.observableArrayList();
        positionList = FXCollections.observableArrayList();
        departmentList.addAll(Lists.getDepartmentList());
        positionList.addAll(Lists.getPositionList());
        box1.setEditable(false);
        box2.setEditable(false);
        box1.getItems().setAll(departmentList);
        box2.getItems().setAll(positionList);
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "/com/ntu/api/spring/database/config.xml");
        TeacherServiceInt teacherService = context.getBean(TeacherServiceInt.class);
        teacherService.addTeacher(new Teacher(text1.getText(),text2.getText(),
                Lists.getPositionList().get(box2.getSelectionModel().getSelectedIndex()),
                Lists.getDepartmentService().getDepartments().get(box1.getSelectionModel().getSelectedIndex())));
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addTeacher.getScene().getWindow());
        dlg.close();
    }
}
