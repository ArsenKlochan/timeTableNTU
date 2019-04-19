package com.ntu.api.controller.main;

import com.ntu.api.domain.database.entity.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TimeTableCreateController {
    @FXML private AnchorPane create;
    @FXML private ComboBox<String> faculty;
    @FXML private ComboBox<String> curriculum;
    @FXML private ComboBox<String> department;
    @FXML private ComboBox<String> speciality;
    @FXML private ComboBox<String> course;
    @FXML private ComboBox<String> group;

    private String str;

    private static ObservableList<String> facultyList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> departmentList;
    private static ObservableList<String> specialityList;
    private static ObservableList<String> courseList;
    private static ObservableList<String> groupList;

    private static ArrayList<Faculty> faculties = new ArrayList();
    private static ArrayList<Speciality> curriculuses = new ArrayList();
    private static ArrayList<Department> departments = new ArrayList();
    private static ArrayList<Curriculum> specialities = new ArrayList();
    private static ArrayList<Course> courses = new ArrayList();
    private static ArrayList<Group> groups = new ArrayList();

    @FXML public void initialize(){

    }

    @FXML public void facultyChoose(){

    }

    @FXML public void curriculumChoose(){

    }

    @FXML public void departmentChoose(){

    }

    @FXML public void specialityChoose(){

    }

    @FXML public void courseChoose(){

    }

    @FXML public void groupChoose(){

    }

    @FXML public void saveOnClick(){

    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) create.getScene().getWindow();
        dlg.close();
    }

    @FXML public void addClass(ActionEvent event){
        Button btn = (Button) event.getSource();
        Integer temp = Integer.parseInt(btn.getId().replace("b", ""));
        str = temp/100 + "." + temp%100/10 + "." + temp%10;
        System.out.println(str);
    }

}
