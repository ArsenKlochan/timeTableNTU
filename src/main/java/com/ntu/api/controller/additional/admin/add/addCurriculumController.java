package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
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

import java.io.File;

public class addCurriculumController {
    @FXML private AnchorPane addSpeciality;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private ComboBox<String> box3;
    @FXML private Button button1;
    @FXML private Button button2;

    private static ObservableList<String> specialityList;
    private static ObservableList<String> departmentList;
    private static ObservableList<String> qualificationList;

    private ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    private CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);

    @FXML public void initialize(){
        label1.setText("Назва освітньої програми");
        label2.setText("Кваліфікація");
        label3.setText("Спеціальність");
        label4.setText("Кафедра");
        label5.setText("Коротка назва ОП");
        button1.textProperty().set("Додати освітню програму");

        specialityList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();
        qualificationList = FXCollections.observableArrayList();

        specialityList.addAll(Lists.getSpecialityList());
        departmentList.addAll(Lists.getDepartmentList());
        qualificationList.addAll(Lists.getQualificationList());

        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);

        box1.getItems().setAll(qualificationList);
        box2.getItems().setAll(specialityList);
        box3.getItems().setAll(departmentList);
    }

    @FXML public void okOnClick(){
        curriculumService.addCurriculumCourseSemester(new Curriculum(text1.getText(), text2.getText(),
                Lists.getSpecialityService().getSpecialities().get(box2.getSelectionModel().getSelectedIndex()),
                Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex()),
                Lists.getQualificationList().get(box1.getSelectionModel().getSelectedIndex())));
        clear();
        Message.questionOnClick(addSpeciality,"Додавання освітньої програми", "Додати ще одну освітню програму?");
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addSpeciality.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
    }
}
