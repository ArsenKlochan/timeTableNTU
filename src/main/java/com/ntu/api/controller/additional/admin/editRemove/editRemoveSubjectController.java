package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.ExamType;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
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
    List<Course> courses;
    List<Subjects> subjects;

    private static ObservableList<String> objectList;
    private static ObservableList<String> subjectList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> courseList;;
    private static boolean bool;
    private static Boolean editBool = false;
    Curriculum curriculum;
    Course course;
    Subjects subject;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
    SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);

    public static boolean isBool() {
        return bool;
    }
    public static void setBool(boolean bool) {
        editRemoveSubjectController.bool = bool;
    }

    @FXML public void initialize(){
        subjects = Lists.getSubjectService().getSubjectList();
        editBool = false;
        objectList = FXCollections.observableArrayList();
        curriculumList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();

        label0.setText("Освітня програма");
        label1.setText("Курс");
        label2.setText("Дисципліна");
        label3.setText("Назва дисципліни");
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
        box1.getItems().setAll(courseList);
        box2.getItems().setAll(objectList);

        if(bool){
            button1.textProperty().set("Зберегти дисципліну");
        }
        else {
            button1.textProperty().set("Видалити дисципліну");
            box1.setDisable(true);
            box2.setDisable(true);
            box3.setDisable(true);
            text1.setDisable(true);
            text2.setDisable(true);
            text3.setDisable(true);
            text4.setDisable(true);
            text5.setDisable(true);
        }
    }
    @FXML public void chooseOnClick(){
        editBool = true;
        clear();
        List<String> parameters = new ArrayList<>();
        box2.setDisable(true);
        subject = subjects.get(box2.getSelectionModel().getSelectedIndex());
        parameters = subjectService.getParametersInString(subject);
        text1.setText(parameters.get(0));
        box1.promptTextProperty().set(parameters.get(1));
        text2.setText(parameters.get(2));
        text3.setText(parameters.get(3));
        text4.setText(parameters.get(4));
        text5.setText(parameters.get(5));
        box3.promptTextProperty().set(parameters.get(6));
    }

    @FXML public void curriculumChooseOnClick(){
        curriculum = Lists.getCurriculumService().getCurriculums().get(box0.getSelectionModel().getSelectedIndex());
        clear();
        courseList.addAll(courseService.getCourseOnCurriculuminString(curriculum));
        courses = courseService.getCourseOnCurriculumList(curriculum);
        box1.getItems().setAll(courseList);
    }
    @FXML public void courseChooseOnClick(){
        course = courses.get(box1.getSelectionModel().getSelectedIndex());
        if (editBool){
            subject.setCourse(course);
        }
        else {
            clear();
            objectList.addAll(subjectService.getSubjectOnCourse(course));
            subjects = subjectService.getSubjectOnCourseList(course);
            box2.getItems().setAll(objectList);
        }
    }
    @FXML public void controlTypeChooseOnClick(){
        subject.setExamType(ExamType.values()[box3.getSelectionModel().getSelectedIndex()]);
    }
    @FXML public void okOnClick(){
        if(bool){
            subject.setSubjectName(text1.getText());
            subject.setLection(Integer.parseInt(text2.getText()));
            subject.setPractic(Integer.parseInt(text3.getText()));
            subject.setLabaratory(Integer.parseInt(text4.getText()));
            subject.setAllHours(Integer.parseInt(text5.getText()));
            subjectService.updateSubject(subject);
        }
        else{
            subjectService.deleteSubject(subject);
        }
        clear();
        if(bool){
            Message.questionOnClick(editRemoveSubject, "Редагування дисципліни", "Редагувати ще одну дисципліну?");
        }
        else{
            Message.questionOnClick(editRemoveSubject, "Видалення дисципліни", "Видалити ще одну дисципліну?");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(editRemoveSubject.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
        text1.clear();
        text2.clear();
        text3.clear();
        text4.clear();
        text5.clear();
//        BoxCleaner.boxClear(box0);
//        BoxCleaner.boxClear(box1);
    }
}

