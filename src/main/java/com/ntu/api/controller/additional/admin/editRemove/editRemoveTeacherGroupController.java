package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.TeacherServiceInt;
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

import java.util.ArrayList;
import java.util.List;

public class editRemoveTeacherGroupController {
    @FXML
    private AnchorPane editRemoveTeacherGroup;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox box;
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static ObservableList<String> objectList;
    private static ObservableList<String> parameterOneList;
    private static ObservableList<String> parameterTwoList;
    private Teacher teacher;
    private Group group;
    private Curriculum curriculum;

    private static ApplicationContext context = new ClassPathXmlApplicationContext(
            "/com/ntu/api/spring/database/config.xml");
    private static TeacherServiceInt teacherService = context.getBean(TeacherServiceInt.class);
    private static CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    private static GroupServiceInt groupService = context.getBean(GroupServiceInt.class);
    private static int flag;
    private static boolean bool;

    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        editRemoveTeacherGroupController.flag = flag;
    }
    public static boolean isBool() {
        return bool;
    }
    public static void setBool(boolean bool) {
        editRemoveTeacherGroupController.bool = bool;
    }

    @FXML public void initialize(){
        objectList = FXCollections.observableArrayList();
        parameterOneList = FXCollections.observableArrayList();
        parameterTwoList = FXCollections.observableArrayList();
        if(flag==1) {
            label0.setText("Викладач");
            label1.setText("Прізвище");
            label2.setText("Ім'я Побатькові");
            label3.setText("Кафедра");
            label4.setText("Посада");
            if (bool) {
                button1.textProperty().set("Зберегти викладача");
            } else {
                button1.textProperty().set("Видалити викладача");
                box1.setDisable(true);
                box2.setDisable(true);
            }
            objectList.addAll(Lists.getTeacherList());
            parameterOneList.addAll(Lists.getDepartmentList());
            parameterTwoList.addAll(Lists.getPositionList());
            box2.getItems().setAll(parameterTwoList);
        }
        else{
            label0.setText("Група");
            label1.setText("Назва групи");
            label2.setText("Кількість студентів");
            label3.setText("Освітня програма");
            label4.setText("Курс");
            if(bool) {
                button1.textProperty().set("Додати групу");
            }
            else {
                button1.textProperty().set("Видалити групу");
            }
            objectList.addAll(Lists.getGroupeList());
            parameterOneList.addAll(Lists.getCurriculumList());
        }
            box.setEditable(false);
            box1.setEditable(false);
            box2.setEditable(false);
            box.getItems().setAll(objectList);
            box1.getItems().setAll(parameterOneList);
    }

    @FXML public void chooseOnClick(){
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        List<String> parameters = new ArrayList<>();
        if(flag==1){
            teacher = teacherService.getTeachers().get(box.getSelectionModel().getSelectedIndex());
            parameters = teacherService.getParametersInString(teacher);
        }
        else{
            group = groupService.getGroups().get(box.getSelectionModel().getSelectedIndex());
            parameters = groupService.getParametersInString(group);
            box1.promptTextProperty().set(group.getCourse().getCurriculum().getCurriculumName());
        }
        text1.setText(parameters.get(0));
        text2.setText(parameters.get(1));
        box1.promptTextProperty().set(parameters.get(2));
        box2.promptTextProperty().set(parameters.get(3));
    }

    @FXML public void box1OnClick(){
        if (flag==1){
            teacher.setDepartment(Lists.getDepartmentService().getDepartments().get(box1.getSelectionModel().getSelectedIndex()));
        }
        else {
            curriculum = curriculumService.getCurriculums().get(box1.getSelectionModel().getSelectedIndex());
            parameterTwoList.addAll(Lists.getCourseList(curriculum));
            box2.getItems().setAll(parameterTwoList);
        }
    }

    @FXML public void box2OnClick(){
        if(flag==1){
            teacher.setTeacherPosition(Lists.getPositionList().get(box2.getSelectionModel().getSelectedIndex()));
        }
        else {
            group.setCourse(curriculum.getCourses().get(box2.getSelectionModel().getSelectedIndex()));
        }

    }

    @FXML public void okOnClick(){
        if(flag==1) {
            if (bool) {
                teacher.setTeacherSurname(text1.getText());
                teacher.setTeacherName(text2.getText());
                teacherService.updateTeacher(teacher);
            } else {
                teacherService.deleteTeacher(teacher);
            }
        }
        else{
            if (bool) {
                group.setGroupName(text1.getText());
                group.setStudentsNumber(Integer.parseInt(text2.getText()));
                groupService.updateGroupe(group);
            } else {
                groupService.deleteGroupe(group);
            }
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(editRemoveTeacherGroup.getScene().getWindow());
        dlg.close();
    }
}