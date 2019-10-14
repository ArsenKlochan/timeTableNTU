package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
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

import java.util.List;

@Transactional
public class editRemoveDepartmentController {
    @FXML private AnchorPane editRemoveDepartment;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox<String> box;
    @FXML private ComboBox<String> box1;
    @FXML private Button button1;
    @FXML private Button button2;
    private Faculty faculty;
    private static Boolean bool;
    private static Boolean editBool = false;
    private static Boolean enterBool;

    private static ObservableList<String> objectList;
    private static ObservableList<String> parametersList;
    private static Department department;
    private static List<Department> departments;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    DepartmentServiceInt departmentService = context.getBean(DepartmentServiceInt.class);
    FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveDepartmentController.bool = bool;
    }

    @FXML public void initialize(){
        editBool = false;
        enterBool = true;
        objectList = FXCollections.observableArrayList();
        parametersList = FXCollections.observableArrayList();
        label0.setText("Кафедра");
        label1.setText("Код кафедри");
        label2.setText("Назва кафедри");
        label3.setText("Факультет");
        if (bool){
            button1.textProperty().set("Зберегти кафедру");
        }
        else{
            button1.textProperty().set("Видалити кафедру");
            text1.setDisable(true);
            text2.setDisable(true);
        }
        objectList.setAll(Lists.getDepartmentList());
        parametersList.setAll(Lists.getFacultyList());
        box.setEditable(false);
        box1.setEditable(false);
        box.getItems().setAll(objectList);
        box1.getItems().setAll(parametersList);
        departments = departmentService.getDepartments();
    }
    @FXML public void okOnClick(){
        enterBool = false;
       if(bool){
          department.setDepartmentCode(text1.getText());
          department.setDepartmentName(text2.getText());
          departmentService.updateDepartment(department);
       }
       else {
           departmentService.deleteDepartment(department);
       }
        clear();
        initialize();
        box1.setDisable(false);
        if(bool){
            Message.questionOnClick(editRemoveDepartment, "Редагування кафедри", "Редагувати ще одну кафедру?");
        }
        else{
            Message.questionOnClick(editRemoveDepartment, "Видалення кафедри", "Видалити ще одну кафедру?");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveDepartment.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if(enterBool) {
            faculty = facultyService.getFaculties().get(box1.getSelectionModel().getSelectedIndex());
            if (editBool) {
                department.setFaculty(faculty);
            } else {
                boxClear(box);
                objectList.setAll(departmentService.getDepartmentsOnFaculty(faculty));
                departments = departmentService.getDepartmentsOnFacultyList(faculty);
                box.getItems().setAll(objectList);
            }
        }
    }
    @FXML public void chooseOnClick(){
        if(enterBool) {
            if (!editBool && !bool) {
                box1.setDisable(true);
            }
            editBool = true;
            List<String> parameters;
            department = departments.get(box.getSelectionModel().getSelectedIndex());
            parameters = departmentService.getParametersInString(department);
            text1.setText(parameters.get(0));
            text2.setText(parameters.get(1));
            box1.setValue(parameters.get(2));
        }
    }

    private void boxClear(ComboBox<String> box){
        objectList.clear();
        box.promptTextProperty().setValue("");
        box.setValue("");
    }
    private void clear(){
        BoxCleaner.boxClear(box);
        BoxCleaner.boxClear(box1);
        text1.setText("");
        text2.setText("");
    }

}
