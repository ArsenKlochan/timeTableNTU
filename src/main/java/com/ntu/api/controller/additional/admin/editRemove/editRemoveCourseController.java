package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
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
            box1.setDisable(true);
        }
        objectsList.addAll(Lists.getCourseList(course.getCurriculum()));
        parametersList.addAll(Lists.getCurriculumList());
        box.setEditable(false);
        box1.setEditable(false);
        box.getItems().setAll(objectsList);
        box1.getItems().setAll(parametersList);
    }
    @FXML public void okOnClick(){
        if(bool){
            course.setCourseName(text1.getText());
            courseService.updateCourse(course);
        }
        else{
            courseService.deleteCourse(course);
        }
        clear();
        if(bool){
            Message.questionOnClick(removeDeleteCourse,"Редагування курсу","Редагувати ще один курс?");
        }
        else{
            Message.questionOnClick(removeDeleteCourse,"Видалення курсу","Видалити ще один курс?");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) removeDeleteCourse.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        course.setCurriculum(Lists.getCurriculumService().getCurriculums().get(box1.getSelectionModel().getSelectedIndex()));
    }

    @FXML public void choseOnClick(){
        BoxCleaner.boxClear(box1);
        List<String> parameters = new ArrayList<>();
        course = courseService.getCourses().get(box.getSelectionModel().getSelectedIndex());
        parameters = courseService.getParametersInString(course);
        text1.setText(parameters.get(0));
        box1.promptTextProperty().set(parameters.get(1));
    }
    private void clear(){
        text1.clear();
        BoxCleaner.boxClear(box);
        BoxCleaner.boxClear(box1);
    }
}
