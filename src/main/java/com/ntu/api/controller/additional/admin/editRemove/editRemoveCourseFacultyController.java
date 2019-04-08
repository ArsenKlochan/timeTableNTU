package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class editRemoveCourseFacultyController {
    @FXML private AnchorPane removeDeleteCourseFaculty;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box;
    @FXML private ComboBox<String> box1;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;
    private static int flag;

    private static ObservableList<String> objectsList;
    private static ObservableList<String> parametersList;

    Course course;
    Faculty faculty;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
    FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveCourseFacultyController.bool = bool;
    }
    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        editRemoveCourseFacultyController.flag = flag;
    }

    @FXML public void initialize(){
        objectsList = FXCollections.observableArrayList();
        parametersList = FXCollections.observableArrayList();
        if(flag==1){
            label0.setText("Курс");
            label1.setText("Назва курсу");
            label2.setText("Спеціальність");
            if (bool){
                button1.textProperty().set("Зберегти курс");
            }
            else{
                button1.textProperty().set("Видалити курс");
                box1.setDisable(true);
            }
            objectsList.addAll(Lists.getCourseList());
            parametersList.addAll(Lists.getSpecialityList());
        }
        else{
            label0.setText("Факультет");
            label1.setText("Назва факультету");
            label2.setText("Корпус");
            if (bool){
                button1.textProperty().set("Зберегти факультет");
            }
            else{
                button1.textProperty().set("Видалити факультет");
                box1.setDisable(true);
            }
            objectsList.addAll(Lists.getFacultyList());
            parametersList.addAll(Lists.getBuildingList());

        }
        box.setEditable(false);
        box1.setEditable(false);
        box.getItems().setAll(objectsList);
        box1.getItems().setAll(parametersList);
    }
    @FXML public void okOnClick(){
        if(flag==1){
            if(bool){
                course.setCourseName(text1.getText());
                courseService.updateCourse(course);
            }
            else{
                courseService.deleteCourse(course);
            }
        }
        else{
            if(bool){
                faculty.setFacultyName(text1.getText());
                facultyService.updateFaculty(faculty);
            }
            else{
                facultyService.deleteFacultu(faculty);
            }
        }
        cancelOnClick();
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) removeDeleteCourseFaculty.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if(flag==1){
            course.setSpeciality(Lists.getSpecialityService().getSpecialities().get(box1.getSelectionModel().getSelectedIndex()));
        }
        else{
            faculty.setBuilding(Lists.getBuildingService().getBuildingList().get(box1.getSelectionModel().getSelectedIndex()));
        }

    }
    @FXML public void choseOnClick(){
        BoxCleaner.boxClear(box1);
        List<String> parameters = new ArrayList<>();
        if(flag==1){
            course = courseService.getCourses().get(box.getSelectionModel().getSelectedIndex());
            parameters = courseService.getParametersInString(course);
        }
        else{
            faculty = facultyService.getFaculties().get(box.getSelectionModel().getSelectedIndex());
            parameters = facultyService.getParametersInString(faculty);
        }
        text1.setText(parameters.get(0));
        box1.promptTextProperty().set(parameters.get(1));
    }
}
