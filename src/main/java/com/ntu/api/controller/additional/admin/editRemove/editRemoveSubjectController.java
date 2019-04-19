package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
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

public class editRemoveSubjectController {
    @FXML private AnchorPane editRemoveSubject;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label label6;
    @FXML private Label label7;
    @FXML private Label label8;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private TextField text5;
    @FXML private ComboBox box0;
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private ComboBox box3;
    @FXML private Button button1;
    @FXML private Button button2;

    private static ObservableList<String> objectList;
    private static ObservableList<String> subjectList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> courseList;;
    private boolean bool;
    Curriculum curriculum;
    Course course;
    Subjects subject;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
    SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);

    public boolean isBool() {
        return bool;
    }
    public void setBool(boolean bool) {
        this.bool = bool;
    }

    @FXML public void initialize(){
        objectList = FXCollections.observableArrayList();
        curriculumList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();

        label0.setText("Освітня програма");
        label1.setText("Дисципліна");
        label2.setText("Назва дисципліни");
        label3.setText("Курс");
        label4.setText("Годин лекцій");
        label5.setText("Годин практичних");
        label6.setText("Годин лабораторних");
        label7.setText("Всього годин");
        label8.setText("Підсумковий контроль");
        objectList.addAll(Lists.getSubjectsList());
        curriculumList.addAll(Lists.getCurriculumList());

        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);
        box0.getItems().setAll(curriculumList);
        box1.getItems().setAll(objectList);

        if(bool){
            button1.textProperty().set("Зберегти дисципліну");
        }
        else {
            button1.textProperty().set("Видалити дисципліну");
            box1.setDisable(true);
            box2.setDisable(true);
            box3.setDisable(true);
        }
    }
    @FXML public void chooseOnClick(){
//        BoxCleaner.boxClear(box1);
//        BoxCleaner.boxClear(box2);
//        BoxCleaner.boxClear(box3);
//        List<String> parametrs = new ArrayList<>();
//        subject = subjectService.getSubjectList().get(box1.getSelectionModel().getSelectedIndex());
//        parametrs = subjectService.getParametersInString(subject);


    }

    @FXML public void curriculumChooseOnClick(){
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
        curriculum = curriculumService.getCurriculums().get(box0.getSelectionModel().getSelectedIndex());
//        subjectList.addAll(curriculum.get);

    }
    @FXML public void courseChooseOnClick(){

    }
    @FXML public void controlTypeChooseOnClick(){

    }
    @FXML public void okOnClick(){

    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(editRemoveSubject.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        text3.clear();
        text4.clear();
        text5.clear();
        BoxCleaner.boxClear(box0);
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
    }
}

