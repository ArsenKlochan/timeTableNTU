package com.ntu.api.controller.additional.admin.add;

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

public class addCourseController {
    @FXML private AnchorPane addCourse;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField text1;
    @FXML private ComboBox box1;
    @FXML private Button button1;
    @FXML private Button button2;
    private static ObservableList<String> tempList;

    @FXML public void initialize(){
        tempList = FXCollections.observableArrayList();
        box1.setEditable(false);
            label1.setText("Назва курсу");
            label2.setText("Освітня програма");
            button1.textProperty().set("Додати курс");
            tempList.addAll(Lists.getCurriculumList());
            box1.getItems().setAll(tempList);
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
            CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
            courseService.addCourse(new Course(text1.getText(),
                    Lists.getCurriculumService().getCurriculums().get(box1.getSelectionModel().getSelectedIndex())));
            clear();
        Message.questionOnClick(addCourse,"Додавання курсу","Додати ще один курс?");
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addCourse.getScene().getWindow());
        dlg.close();
    }
    private void clear(){
        text1.clear();
        BoxCleaner.boxClear(box1);
    }
}
