package com.ntu.api.controller.main;

import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.Teacher;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LessonCreaterController {
    @FXML private AnchorPane addLesson;
    @FXML private Label title;
    @FXML private ComboBox<String> classroom;
    @FXML private ComboBox<String> lecturer;
    @FXML private ComboBox<String> subject;
    @FXML private DatePicker dateFrom;
    @FXML private DatePicker dateTo;

    private static ObservableList<String> lecturerList;
    private static ObservableList<String> subjectList;
    private static ObservableList<String> classroomList;

    private static ArrayList<ClassRoom> classrooms = new ArrayList();
    private static ArrayList<Subjects> subjects = new ArrayList();
    private static ArrayList<Teacher> lecturers = new ArrayList();

    @FXML public void initialize(){

    }

    @FXML public void classroomChoose(){

    }

    @FXML public void lecturerChoose(){

    }

    @FXML public void subjectChoose(){

    }

    @FXML public void addOnClick(){

    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) addLesson.getScene().getWindow();
        dlg.close();
    }

    @FXML public void dateFromChoose(){

    }

    @FXML public void dateToChoose(){

    }





}
