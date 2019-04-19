package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.ExamType;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
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

public class addSubjectController {
    @FXML private AnchorPane addSubject;
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
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private ComboBox box3;
    @FXML private Button button1;
    @FXML private Button button2;

    private static ObservableList<String> examtypeList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> courceList;
    private Curriculum curriculum;

    @FXML public void initialize(){
        button1.textProperty().set("Додати дисципліну");
        label1.setText("Назва дисципліни");
        label2.setText("Освітня програма");
        label3.setText("Курс");
        label4.setText("Годин лекцій");
        label5.setText("Годин практичних");
        label6.setText("Годин лабораторних");
        label7.setText("Всього годин");
        label8.setText("Підсумковий контроль");

        examtypeList = FXCollections.observableArrayList();
        curriculumList = FXCollections.observableArrayList();

        examtypeList.addAll(Lists.getExamList());
        curriculumList.addAll(Lists.getCurriculumList());

        box1.setEditable(false);
        box3.setEditable(false);

        box1.getItems().setAll(curriculumList);
        box3.getItems().setAll(examtypeList);
    }

    @FXML public void curriculumChooseOnClick(){
        curriculum = Lists.getCurriculumService().getCurriculums().get(box1.getSelectionModel().getSelectedIndex());
        courceList = FXCollections.observableArrayList();
        courceList.addAll(Lists.getCourseList(curriculum));
        box2.setEditable(false);
        box2.getItems().setAll(courceList);
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);
        subjectService.addSubject(new Subjects(text1.getText(), Integer.parseInt(text2.getText()),
                Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()),
                Integer.parseInt(text5.getText()), ExamType.values()[box3.getSelectionModel().getSelectedIndex()],
                curriculum.getCourses().get(box2.getSelectionModel().getSelectedIndex())));
        clear();
        Message.questionOnClick(addSubject, "Додавання дисципліни", "Додати ще одну дисципліну?");
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addSubject.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        text3.clear();
        text4.clear();
        text5.clear();
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
    }
}
