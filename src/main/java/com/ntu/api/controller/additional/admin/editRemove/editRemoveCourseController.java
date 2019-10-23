package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class editRemoveCourseController {
    @FXML private AnchorPane removeDeleteCourse;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box;
    @FXML private ComboBox<String> box1;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;
    private static Boolean editBool;
    private static Boolean enterBool;
    private static List<Course> courses;

    private static ObservableList<String> objectsList;
    private static ObservableList<String> parametersList;

    Course course;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CourseServiceInt courseService = context.getBean(CourseServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveCourseController.bool = bool;
    }

    @FXML public void initialize(){
        editBool = false;
        enterBool = true;
        objectsList = FXCollections.observableArrayList();
        parametersList = FXCollections.observableArrayList();
        label0.setText("Курс");
        label1.setText("Назва курсу");
        label2.setText("Освітня програма");
        if (bool){
            button1.textProperty().set("Зберегти курс");
        }
        else{
            button1.textProperty().set("Видалити курс");
            text1.setDisable(true);
            box1.setDisable(false);
        }
        objectsList.setAll(Lists.getCourseList());
        parametersList.addAll(Lists.getCurriculumList());
        box.setEditable(false);
        box1.setEditable(false);
        box.getItems().setAll(objectsList);
        box1.getItems().setAll(parametersList);
        courses = Lists.getCourseService().getCourses();
    }
    @FXML public void okOnClick() {
        try {
            editBool = false;
            if (bool) {
                course.setCourseName(text1.getText());
                courseService.updateCourse(course);
            } else {
                courseService.deleteCourse(course);
            }
            clear();
            initialize();
            if (bool) {
                Message.questionOnClick(removeDeleteCourse, "Редагування курсу", "Редагувати ще один курс?");
            } else {
                Message.questionOnClick(removeDeleteCourse, "Видалення курсу", "Видалити ще один курс?");
            }
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(removeDeleteCourse, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) removeDeleteCourse.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if(enterBool) {
            Curriculum curriculum = Lists.getCurriculumService().getCurriculums().get(box1.getSelectionModel().getSelectedIndex());
            if(editBool) {
                course.setCurriculum(curriculum);
            }
            else{
                objectsList.setAll(Lists.getCourseService().getCourseOnCurriculuminString(curriculum));
                courses = courseService.getCourseOnCurriculumList(curriculum);
                box.getItems().setAll(objectsList);
            }
        }
    }

    @FXML public void choseOnClick(){
        if(enterBool) {
            if (!editBool && !bool) {
                box1.setDisable(true);
            }
            enterBool = false;
            editBool = true;
            BoxCleaner.boxClear(box1);
            List<String> parameters;
            course = courses.get(box.getSelectionModel().getSelectedIndex());
            parameters = courseService.getParametersInString(course);
            text1.setText(parameters.get(0));
            box1.setValue(parameters.get(1));
            enterBool = true;
        }
    }
    private void clear(){
        BoxCleaner.boxTwoClear(box, box1);
        text1.clear();
    }
}
