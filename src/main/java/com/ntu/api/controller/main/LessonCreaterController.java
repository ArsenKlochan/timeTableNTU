package com.ntu.api.controller.main;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

    private static List<ClassRoom> classrooms = new ArrayList();
    private static List<Subjects> subjects = new ArrayList();
    private static List<Teacher> lecturers = new ArrayList();

    @FXML public void initialize(){
        classrooms = Lists.getClassRoomService().getClassRoomList();
        lecturers = Lists.getTeacherService().getTeachers();

        classroomList = FXCollections.observableArrayList();
        subjectList = FXCollections.observableArrayList();
        lecturerList = FXCollections.observableArrayList();

        classroomList.addAll(Lists.getClassRoomList());
        lecturerList.addAll(Lists.getTeacherList());

        classroom.setEditable(false);
        lecturer.setEditable(false);
        subject.setEditable(false);

        classroom.getItems().setAll(classroomList);
        lecturer.getItems().setAll(lecturerList);
        subject.getItems().setAll(subjectList);
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
