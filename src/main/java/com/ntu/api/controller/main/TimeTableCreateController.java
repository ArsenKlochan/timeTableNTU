package com.ntu.api.controller.main;

import com.ntu.api.domain.BaseObject;
import com.ntu.api.domain.Lists;
import com.ntu.api.domain.ObjectWithTimeTable;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.timeTable.*;
import com.ntu.api.model.BoxCleaner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.security.timestamp.TimestampToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimeTableCreateController {
    @FXML private AnchorPane create;
    @FXML private ComboBox<String> faculty;
    @FXML private ComboBox<String> curriculum;
    @FXML private ComboBox<String> department;
    @FXML private ComboBox<String> speciality;
    @FXML private ComboBox<String> course;
    @FXML private ComboBox<String> group;
    private UniversityTimeTable universityTimeTable = new UniversityTimeTable();
    private TimeTableObject timeTableObject;
    private String str;

    private static ObservableList<String> facultyList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> departmentList;
    private static ObservableList<String> specialityList;
    private static ObservableList<String> courseList;
    private static ObservableList<String> groupList;

    private Faculty facultyObj;
    private Speciality specialityObj;
    private Department departmentObj;
    private Curriculum curriculumObj;
    private Course courseObj;
    private Group groupObj;

    private boolean faculryBool;
    private boolean specialityBool;
    private boolean departmentBool;
    private boolean curriculumBool;
    private boolean courseBool;
    private boolean groupBool;


    private static List<Faculty> faculties = new ArrayList();
    private static List<Speciality> specialities = new ArrayList();
    private static List<Department> departments = new ArrayList();
    private static List<Curriculum> curriculums = new ArrayList();
    private static List<Course> courses = new ArrayList();
    private static List<Group> groups = new ArrayList();

    @FXML public void initialize(){
        faculties = Lists.getFacultyService().getFaculties();
        specialities = Lists.getSpecialityService().getSpecialities();
        departments = Lists.getDepartmentService().getDepartments();
        curriculums = Lists.getCurriculumService().getCurriculums();
        courses = Lists.getCourseService().getCourses();
        groups = Lists.getGroupService().getGroups();

        facultyList = FXCollections.observableArrayList();
        curriculumList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();
        specialityList = FXCollections.observableArrayList();
        courseList = FXCollections.observableArrayList();
        groupList = FXCollections.observableArrayList();

        facultyList.addAll(Lists.getFacultyList());
        curriculumList.addAll(Lists.getCurriculumList());
        departmentList.addAll(Lists.getDepartmentList());
        specialityList.addAll(Lists.getSpecialityList());
        courseList.addAll(Lists.getCourseList());
        groupList.addAll(Lists.getGroupeList());

        faculty.setEditable(false);
        curriculum.setEditable(false);
        department.setEditable(false);
        speciality.setEditable(false);
        course.setEditable(false);
        group.setEditable(false);

        faculty.getItems().setAll(facultyList);
        curriculum.getItems().setAll(curriculumList);
        department.getItems().setAll(departmentList);
        speciality.getItems().setAll(specialityList);
        course.getItems().setAll(courseList);
        group.getItems().setAll(groupList);
        faculryBool = true;
        specialityBool = true;
        departmentBool = true;
        curriculumBool = true;
        courseBool = true;
        groupBool = true;
        clearBoxies();
    }

    @FXML public void facultyChoose(){
        if(!faculryBool) {
            initialize();
            facultyObj = faculties.get(faculty.getSelectionModel().getSelectedIndex());
            clearBoxies();
            departmentList.setAll(Lists.getDepartmentService().getDepartmentsOnFaculty(facultyObj));
            departments = Lists.getDepartmentService().getDepartmentsOnFacultyList(facultyObj);
            departmentBool = true;
            department.getItems().setAll(departmentList);
            departmentBool = false;
            faculryBool = true;
        }
    }

    @FXML public void curriculumChoose(){
        if(!curriculumBool) {
            curriculumObj = curriculums.get(curriculum.getSelectionModel().getSelectedIndex());
            clearBoxies();
            departmentBool = true;
            department.setValue(Lists.getCurriculumService().getParametersInString(curriculumObj).get(1));
            departmentBool = false;
            specialityBool = true;
            speciality.setValue(Lists.getCurriculumService().getParametersInString(curriculumObj).get(2));
            specialityBool = false;
            courseList.setAll(Lists.getCourseService().getCourseOnCurriculuminString(curriculumObj));
            courses = Lists.getCourseService().getCourseOnCurriculumList(curriculumObj);
            curriculumBool = true;
            course.getItems().setAll(courseList);
            curriculumBool = false;
            curriculumBool = true;
        }
    }

    @FXML public void departmentChoose(){
        if(!departmentBool) {
            departmentObj = departments.get(department.getSelectionModel().getSelectedIndex());
            specialityBool = true;
            clearBoxies();
            faculryBool = true;
            faculty.setValue(Lists.getDepartmentService().getParametersInString(departmentObj).get(2));
            faculryBool = false;
            curriculumList.setAll(Lists.getCurriculumService().getCurriculumsByDepartmentNames(departmentObj));
            curriculums = Lists.getCurriculumService().getCurriculumByDepartment(departmentObj);
            curriculumBool = true;
            curriculum.getItems().setAll(curriculumList);
            curriculumBool = false;
            departmentBool = true;
        }
    }

    @FXML public void specialityChoose(){
        if(!specialityBool) {
            initialize();
            specialityObj = specialities.get(speciality.getSelectionModel().getSelectedIndex());
            clearBoxies();
            curriculumList.setAll(Lists.getCurriculumService().getCurriculumsBySpecialityNames(specialityObj));
            curriculums = Lists.getCurriculumService().getCurriculumsBySpeciality(specialityObj);
            curriculumBool = true;
            curriculum.getItems().setAll(curriculumList);
            curriculumBool = false;
            specialityBool = true;
        }
    }

    @FXML public void courseChoose(){
        if(!courseBool) {
            courseObj = courses.get(course.getSelectionModel().getSelectedIndex());
            clearBoxies();
            curriculumBool = true;
            curriculum.setValue(Lists.getCourseService().getParametersInString(courseObj).get(1));
            curriculumBool = false;
            groupList.setAll(Lists.getGroupService().getGroupOnCourse(courseObj));
            groups = Lists.getGroupService().getGroupOnCourseList(courseObj);
            groupBool = true;
            group.getItems().setAll(groupList);
            groupBool = false;
            courseBool = true;
        }
    }

    @FXML public void groupChoose(){
        if(!groupBool) {
            groupObj = groups.get(group.getSelectionModel().getSelectedIndex());
            clearBoxies();
            courseBool = true;
            course.setValue(Lists.getGroupService().getParametersInString(groupObj).get(3));
            courseBool = false;
            groupBool = true;
            groupObj.setTimeTable(new TimeTableObject());
            insertLesonsOnWindows(groupObj);
            timeTableObject = new TimeTableObject();
            timeTableObject.setName(groupObj.getGroupName() + " ");
        }
    }

    @FXML public void saveOnClick(){
        universityTimeTable.add(timeTableObject);
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) create.getScene().getWindow();
        dlg.close();
    }

    @FXML public void addClass(ActionEvent event){
        Stage addLesson = new Stage();
        addLesson.setTitle("Додавання заняття");
        addLesson.setResizable(false);

        AnchorPane addLes = null;
        try {
            addLes = FXMLLoader.load(getClass().getResource("/com/ntu/api/javafx/model/main/lessonCreater.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
//            Message.errorCatch(create,"Error", "Add lesson Error");
        }
        addLesson.initOwner(create.getScene().getWindow());
        addLesson.initModality(Modality.WINDOW_MODAL);
        addLesson.setScene(new Scene(addLes));
        addLesson.show();

        Button btn = (Button) event.getSource();
        Integer temp = Integer.parseInt(btn.getId().replace("b", ""));
        str = temp/100 + "." + temp%100/10 + "." + temp%10;
        System.out.println(str);
    }

    private void clearBoxies(){
        if(faculryBool) {
            BoxCleaner.boxClear(faculty);
            faculryBool = false;
        }
        if(specialityBool) {
            BoxCleaner.boxClear(speciality);
            specialityBool = false;
        }
        if(departmentBool) {
            BoxCleaner.boxClear(department);
            departmentBool = false;
        }
        if(curriculumBool) {
            BoxCleaner.boxClear(curriculum);
            curriculumBool = false;
        }
        if(courseBool) {
            BoxCleaner.boxClear(course);
            courseBool = false;
        }
        if(groupBool) {
            BoxCleaner.boxClear(group);
            groupBool = false;
        }
    }

    private void insertLesonsOnWindows(ObjectWithTimeTable object){
        TimeTableObject timeTable = object.getTimeTable();
        System.out.println(timeTable);
        System.out.println(timeTable.getWeeks().length);
        System.out.println(timeTable.getWeeks()[0].getDayList().length);
        System.out.println(timeTable.getWeeks()[0].getDayList()[0].getLessonsList().length);
        for(int i = 0; i < timeTable.getWeeks().length; i++){
            for(int j = 0; j < timeTable.getWeeks()[i].getDayList().length; j++){
                for(int k = 0; k < timeTable.getWeeks()[i].getDayList()[j].getLessonsList().length; k++){
                    CurrentLesson lesson = timeTable.getWeeks()[i].getDayList()[j].getLessonsList()[k];
                    int temp = (i+1)*100 + (j+1)*10 + (k+1);
                    String name = "b"+temp;
                    Button temp1 = (Button) create.lookup(name);
                    temp1.setText(lesson.getParametersToView());
                }
            }
        }

    }


}
