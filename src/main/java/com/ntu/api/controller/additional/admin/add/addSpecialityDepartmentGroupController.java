package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
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

public class addSpecialityDepartmentGroupController {
    @FXML private AnchorPane addCurriculumDepartment;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label4;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox box1;
    @FXML private Button button1;
    @FXML private Button button2;

    private static int counter;
    private static ObservableList<String> facultyList;
    private static ObservableList<String> courseyList;

    public static void setCounter(int counter) {
        addSpecialityDepartmentGroupController.counter = counter;
    }

    @FXML public void initialize(){
        if(counter==1){
            label1.setText("Код спеціальності");
            label2.setText("Назва спеціальності");
            label4.setText("Факультет");
            button1.textProperty().set("Додати спеціальність");
            facultyList  = FXCollections.observableArrayList();
            facultyList.addAll(Lists.getFacultyList());
            box1.setEditable(false);
            box1.getItems().setAll(facultyList);
        }
        if(counter==2){
            label1.setText("Код кафедри");
            label2.setText("Назва кафедри");
            label4.setText("Факультет");
            button1.textProperty().set("Додати кафедру");
            facultyList  = FXCollections.observableArrayList();
            facultyList.addAll(Lists.getFacultyList());
            box1.setEditable(false);
            box1.getItems().setAll(facultyList);
        }
        if(counter==3){
            label1.setText("Назва групи");
            label2.setText("Кількість студентів");
            label4.setText("Курс");
            button1.textProperty().set("Додати групу");
            courseyList = FXCollections.observableArrayList();
            courseyList.addAll(Lists.getCourseList());
            box1.setEditable(false);
            box1.getItems().setAll(courseyList);
        }
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        if(counter==1){
            SpecialityServiceInt specialityService = context.getBean(SpecialityServiceInt.class);
            specialityService.addSpeciality(new Speciality(text1.getText(), text2.getText(),
                    Lists.getFacultyService().getFaculties().get(box1.getSelectionModel().getSelectedIndex())));
        }
        if(counter==2){
            DepartmentServiceInt departmentService = context.getBean(DepartmentServiceInt.class);
            departmentService.addDepartment(new Department(text1.getText(), text2.getText(),
                    Lists.getFacultyService().getFaculties().get(box1.getSelectionModel().getSelectedIndex())));
        }
        if(counter==3){
            GroupServiceInt groupService = context.getBean(GroupServiceInt.class);
            groupService.addGroupe(new Group(text1.getText(), Integer.parseInt(text2.getText()),
                    Lists.getCourseService().getCourses().get(box1.getSelectionModel().getSelectedIndex())));
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addCurriculumDepartment.getScene().getWindow());
        dlg.close();
    }
}
