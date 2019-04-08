package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
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
public class editRemoveCurriculumDepartmentGroupController {
    @FXML private AnchorPane editRemoveCurriculumDepartmentGroup;
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
    private static Boolean bool;
    private static  int flag;

    private static ObservableList<String> objectList;
    private static ObservableList<String> parametersList;

    Curriculum curriculum;
    Department department;
    Group group;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    DepartmentServiceInt departmentService = context.getBean(DepartmentServiceInt.class);
    GroupServiceInt groupService = context.getBean(GroupServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveCurriculumDepartmentGroupController.bool = bool;
    }
    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        editRemoveCurriculumDepartmentGroupController.flag = flag;
    }

    @FXML public void initialize(){
        objectList = FXCollections.observableArrayList();
        parametersList = FXCollections.observableArrayList();
        if(flag==1){
            label0.setText("Освітня програма");
            label1.setText("Код освітньої програми");
            label2.setText("Назва освітньої програми");
            label3.setText("Факультет");
            if (bool){
                button1.textProperty().set("Зберегти освітню програму");
            }
            else{
                button1.textProperty().set("Видалити освітню програму");
                box1.setDisable(true);
            }
            objectList.addAll(Lists.getCurriculumList());
            parametersList.addAll(Lists.getFacultyList());
        }
        else if(flag==2){
            label0.setText("Кафедра");
            label1.setText("Код кафедри");
            label2.setText("Назва кафедри");
            label3.setText("Факультет");
            if (bool){
                button1.textProperty().set("Зберегти кафедру");
            }
            else{
                button1.textProperty().set("Видалити кафедру");
                box1.setDisable(true);
            }
            objectList.addAll(Lists.getDepartmentList());
            parametersList.addAll(Lists.getFacultyList());
        }
        else{
            label0.setText("Група");
            label1.setText("Назва групи");
            label2.setText("Кількість студентів");
            label3.setText("Курс");
            if (bool){
                button1.textProperty().set("Зберегти групу");
            }
            else{
                button1.textProperty().set("Видалити групу");
                box1.setDisable(true);
            }
            objectList.addAll(Lists.getGroupeList());
            parametersList.addAll(Lists.getCourseList());

        }
        box.setEditable(false);
        box1.setEditable(false);
        box.getItems().setAll(objectList);
        box1.getItems().setAll(parametersList);
    }
    @FXML public void okOnClick(){
        if(flag==1){
            if(bool){
                curriculum.setCurriculumCode(text1.getText());
                curriculum.setCurriculumName(text2.getText());
                curriculumService.updateCurriculum(curriculum);
            }
            else{
                curriculumService.deleteCurriculum(curriculum);
            }
        }
        else if(flag==2){
            if(bool){
                department.setDepartmentCode(text1.getText());
                department.setDepartmentName(text2.getText());
                departmentService.updateDepartment(department);
            }
            else{
                departmentService.deleteDepartment(department);
            }
        }
        else{
            if(bool){
                group.setGroupName(text1.getText());
                group.setStudentsNumber(Integer.parseInt(text2.getText()));
                groupService.updateGroupe(group);
            }
            else{
                groupService.deleteGroupe(group);
            }
        }
        cancelOnClick();
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveCurriculumDepartmentGroup.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if (flag==1){
            curriculum.setFaculty(Lists.getFacultyService().getFaculties().get(box1.getSelectionModel().getSelectedIndex()));
        }
        else if(flag==2){
            department.setFaculty(Lists.getFacultyService().getFaculties().get(box1.getSelectionModel().getSelectedIndex()));
        }
        else{
            group.setCourse(Lists.getCourseService().getCourses().get(box1.getSelectionModel().getSelectedIndex()));
        }
    }
    @FXML public void chooseOnClick(){
        BoxCleaner.boxClear(box1);
        List<String> parameters = new ArrayList<>();
        if(flag==1){
            curriculum = curriculumService.getCurriculums().get(box.getSelectionModel().getSelectedIndex());
            parameters = curriculumService.getParametersInString(curriculum);
        }
        else if(flag==2){
            department = departmentService.getDepartments().get(box.getSelectionModel().getSelectedIndex());
            parameters = departmentService.getParametersInString(department);
        }
        else{
            group = groupService.getGroups().get(box.getSelectionModel().getSelectedIndex());
            parameters = groupService.getParametersInString(group);
        }
        text1.setText(parameters.get(0));
        text2.setText(parameters.get(1));
        box1.promptTextProperty().set(parameters.get(3));
    }
}
