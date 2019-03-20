package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Subject;
import com.ntu.api.domain.database.entity.enums.ExamType;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
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

public class addSubjectController {
    @FXML AnchorPane addSubject;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML Label label6;
    @FXML Label label7;
    @FXML Label label8;
    @FXML TextField text1;
    @FXML TextField text2;
    @FXML TextField text3;
    @FXML TextField text4;
    @FXML TextField text5;
    @FXML ComboBox box1;
    @FXML ComboBox box2;
    @FXML ComboBox box3;
    @FXML Button button1;
    @FXML Button button2;

    private static ObservableList<String> examtypeList;
    private static ObservableList<String> specialitiesList;
    private static ObservableList<String> courceList;

    @FXML public void initialize(){
        button1.textProperty().set("Додати дисципліну");
        label1.setText("Назва дисципліни");
        label2.setText("Спеціальність");
        label3.setText("Курс");
        label4.setText("Годин лекцій");
        label5.setText("Годин практичних");
        label6.setText("Годин лабораторних");
        label7.setText("Всього годин");
        label8.setText("Підсумковий контроль");

        examtypeList = FXCollections.observableArrayList();
        specialitiesList = FXCollections.observableArrayList();
        courceList = FXCollections.observableArrayList();

        examtypeList.addAll(Lists.getExamList());
        specialitiesList.addAll(Lists.getSpecialityList());
        courceList.addAll(Lists.getCourseList());

        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);

        box1.getItems().setAll(examtypeList);
        box2.getItems().setAll(specialitiesList);
        box3.getItems().setAll(courceList);
    }
    @FXML public void boxOneOnClick(){}
    @FXML public void boxTwoOnClick(){}
    @FXML public void boxThreeOnClick(){}

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);
        subjectService.addSubject(new Subject(text1.getText(),
                Lists.getCourseService().getCourses().get(box2.getSelectionModel().getSelectedIndex()),
                Integer.parseInt(text2.getText()), Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()),
                Integer.parseInt(text5.getText()), ExamType.values()[box3.getSelectionModel().getSelectedIndex()]));
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addSubject.getScene().getWindow());
        dlg.close();
    }
}
