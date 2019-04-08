package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
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

public class addCourseFacultyController {
    @FXML private AnchorPane addCourse;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField text1;
    @FXML private ComboBox box1;
    @FXML private Button button1;
    @FXML private Button button2;
    private static ObservableList<String> tempList;
    private static int counter;

    public static void setCounter(int counter) {
        addCourseFacultyController.counter = counter;
    }

    @FXML public void initialize(){
        tempList = FXCollections.observableArrayList();
        box1.setEditable(false);
        if(counter==1){
            label1.setText("Назва курсу");
            label2.setText("Спеціальність");
            button1.textProperty().set("Додати курс");
            tempList.addAll(Lists.getSpecialityList());
            box1.getItems().setAll(tempList);
        }
        if(counter==2){
            label1.setText("Назва факультету");
            label2.setText("Корпус");
            button1.textProperty().set("Додати факультет");
            tempList.addAll(Lists.getBuildingList());
            box1.getItems().setAll(tempList);
        }
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        if(counter==1) {
            CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
            courseService.addCourse(new Course(text1.getText(),
                    Lists.getSpecialityService().getSpecialities().get(box1.getSelectionModel().getSelectedIndex())));
        }
        if(counter==2) {
            FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);
            facultyService.addFaculty(new Faculty(text1.getText(),
                    Lists.getBuildingService().getBuildingList().get(box1.getSelectionModel().getSelectedIndex())));
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addCourse.getScene().getWindow());
        dlg.close();
    }
}
