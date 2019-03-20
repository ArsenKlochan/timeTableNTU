package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
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

public class addCurriculumDepartmentGroupController {
    @FXML AnchorPane addCurriculumDepartment;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label4;
    @FXML TextField text1;
    @FXML TextField text2;
    @FXML ComboBox box1;
    @FXML Button button1;
    @FXML Button button2;

    private static int counter;
    private static ObservableList<String> facultyList;
    private static ObservableList<String> courseyList;

    public static void setCounter(int counter) {
        addCurriculumDepartmentGroupController.counter = counter;
    }

    @FXML public void initialize(){
        if(counter==1){
            label1.setText("Код освітньої програми");
            label2.setText("Назва освітньої програми");
            label4.setText("Факультет");
            button1.textProperty().set("Додати освітню програму");
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
            CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
            curriculumService.addCurriculum(new Curriculum(text1.getText(), text2.getText(),
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
