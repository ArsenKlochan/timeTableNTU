package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Semester;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.ExamType;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SemesterServiceInt;
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
import org.springframework.dao.DataIntegrityViolationException;

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
    @FXML private Label label9;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private TextField text3;
    @FXML private TextField text4;
    @FXML private TextField text5;
    @FXML private ComboBox box0;
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private ComboBox box3;
    @FXML private ComboBox box4;
    @FXML private Button button1;
    @FXML private Button button2;
    List<Course> courses;
    List<Subjects> subjects;
    List<Semester> semesters;

    private static ObservableList<String> objectList;
    private static ObservableList<String> subjectList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> courseList;
    private static ObservableList<String> semestrList;
    private static boolean bool;
    private static Boolean editBool = false;
    private static Boolean enterBool;
    Curriculum curriculum;
    Course course;
    Semester semester;
    Subjects subject;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
    SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);
    SemesterServiceInt semesterService = context.getBean(SemesterServiceInt.class);

    public static boolean isBool() {
        return bool;
    }
    public static void setBool(boolean bool) {
        editRemoveSubjectController.bool = bool;
    }

    @FXML public void initialize(){
        editBool = false;
        enterBool = true;
        subjects = Lists.getSubjectService().getSubjectList();
        courses = Lists.getCourseService().getCourses();
        objectList = FXCollections.observableArrayList();
        curriculumList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
        semestrList = FXCollections.observableArrayList();

        label0.setText("Освітня програма");
        label1.setText("Курс");
        label2.setText("Дисципліна");
        label3.setText("Назва дисципліни");
        label4.setText("Годин лекцій");
        label5.setText("Годин практичних");
        label6.setText("Годин лабораторних");
        label7.setText("Всього годин");
        label8.setText("Підсумковий контроль");
        label9.setText("Семестр");
        objectList.setAll(Lists.getSubjectsList());
        curriculumList.setAll(Lists.getCurriculumList());
        courseList.setAll(Lists.getCourseList());
        semestrList.setAll(Lists.getSemesterList());


        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);
        box4.setEditable(false);
        box0.getItems().setAll(curriculumList);
        box1.getItems().setAll(courseList);
        box2.getItems().setAll(objectList);
        box3.getItems().setAll(ExamType.values());
        box4.getItems().setAll(semestrList);

        if(bool){
            button1.textProperty().set("Зберегти дисципліну");
            box3.setDisable(true);
        }
        else {
            button1.textProperty().set("Видалити дисципліну");
            box1.setDisable(false);
            box2.setDisable(false);
            box3.setDisable(true);
            text1.setDisable(true);
            text2.setDisable(true);
            text3.setDisable(true);
            text4.setDisable(true);
            text5.setDisable(true);
        }
    }
    @FXML public void chooseOnClick(){
        if(enterBool) {
            if (!editBool && !bool) {
                box0.setDisable(true);
                box1.setDisable(true);
                box3.setDisable(true);
                box4.setDisable(true);
            }
            else{
                box3.setDisable(false);
            }
            enterBool = false;
            editBool = true;
            List<String> parameters;
            subject = subjects.get(box2.getSelectionModel().getSelectedIndex());
            parameters = subjectService.getParametersInString(subject);
            text1.setText(parameters.get(0));
            box1.setValue(parameters.get(1));
            text2.setText(parameters.get(2));
            text3.setText(parameters.get(3));
            text4.setText(parameters.get(4));
            text5.setText(parameters.get(5));
            box3.setValue(parameters.get(6));
            enterBool = true;
        }
    }


    @FXML public void semesterOnClick(){
        if(enterBool) {
            semester = semesters.get(box4.getSelectionModel().getSelectedIndex());
            if (editBool) {
                subject.setSemester(semester);
            } else {
                objectList.setAll(subjectService.getSubjectOnSemester(semester));
                subjects = subjectService.getSubjectOnSemesterList(semester);
                box2.getItems().setAll(objectList);
            }
        }
    }

    @FXML public void curriculumChooseOnClick(){
        if(enterBool) {
            curriculum = Lists.getCurriculumService().getCurriculums().get(box0.getSelectionModel().getSelectedIndex());
            courseList.setAll(courseService.getCourseOnCurriculuminString(curriculum));
            courses = courseService.getCourseOnCurriculumList(curriculum);
            box1.getItems().setAll(courseList);
        }
    }
    @FXML public void courseChooseOnClick(){
        if(enterBool) {
            course = courses.get(box1.getSelectionModel().getSelectedIndex());
            semestrList.setAll(semesterService.getSemestersOnCourseInString(course));
            semesters = semesterService.getSemesterOnCourseList(course);
            box4.getItems().setAll(semestrList);
        }
    }
    @FXML public void controlTypeChooseOnClick(){
        if(editBool && enterBool) {
            subject.setExamType(ExamType.values()[box3.getSelectionModel().getSelectedIndex()]);
        }
    }
    @FXML public void okOnClick(){
        try {
            enterBool = false;
            if (bool) {
                subject.setSubjectName(text1.getText());
                subject.setLection(Integer.parseInt(text2.getText()));
                subject.setPractic(Integer.parseInt(text3.getText()));
                subject.setLabaratory(Integer.parseInt(text4.getText()));
                subject.setAllHours(Integer.parseInt(text5.getText()));
                subjectService.updateSubject(subject);
            } else {
                subjectService.deleteSubject(subject);
            }
            clear();
            initialize();
            if (bool) {
                Message.questionOnClick(editRemoveSubject, "Редагування дисципліни", "Редагувати ще одну дисципліну?");
            } else {
                Message.questionOnClick(editRemoveSubject, "Видалення дисципліни", "Видалити ще одну дисципліну?");
            }
        }
        catch (NumberFormatException e){
            Message.errorCatch(editRemoveSubject, "Помилка введення", "Перевірте правильність введення числових даних");
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(editRemoveSubject, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
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
        BoxCleaner.boxClear(box0);
//        BoxCleaner.boxClear(box1);
    }
}

