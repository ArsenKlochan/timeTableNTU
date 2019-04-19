package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.TeacherServiceInt;
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

public class addTeacherGroupController {
    @FXML private AnchorPane addTeacherGroup;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox box1;
    @FXML private ComboBox box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static int counter;
    private static ObservableList<String> departmentList;
    private static ObservableList<String> positionList;
    private static ObservableList<String> curriculumList;
    private static ObservableList<String> courseList;
    private static ApplicationContext context = new ClassPathXmlApplicationContext(
            "/com/ntu/api/spring/database/config.xml");
    private static TeacherServiceInt teacherService = context.getBean(TeacherServiceInt.class);
    private static CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    private static GroupServiceInt groupService = context.getBean(GroupServiceInt.class);

    public static int getCounter() {
        return counter;
    }
    public static void setCounter(int counter) {
        addTeacherGroupController.counter = counter;
    }

    @FXML public void initialize(){
        if(counter==1) {
            label1.setText("Прізвище");
            label2.setText("Ім'я Побатькові");
            label3.setText("Кафедра");
            label4.setText("Посада");
            button1.textProperty().set("Додати викладача");
            departmentList = FXCollections.observableArrayList();
            positionList = FXCollections.observableArrayList();
            departmentList.addAll(Lists.getDepartmentList());
            positionList.addAll(Lists.getPositionList());
            box1.setEditable(false);
            box2.setEditable(false);
            box1.getItems().setAll(departmentList);
            box2.getItems().setAll(positionList);
        }
        else{
            label1.setText("Назва групи");
            label2.setText("Кількість студентів");
            label3.setText("Освітня програма");
            label4.setText("Курс");
            button1.textProperty().set("Додати групу");
            curriculumList = FXCollections.observableArrayList();
            courseList = FXCollections.observableArrayList();
            curriculumList.addAll(Lists.getCurriculumList());
            box1.setEditable(false);
            box2.setEditable(false);
            box1.getItems().setAll(curriculumList);
        }
    }

    @FXML public void box1ChooseOnClick(){
        Curriculum  curriculum = curriculumService.getCurriculums().get(box1.getSelectionModel().getSelectedIndex());
        courseList.addAll(Lists.getCourseList(curriculum));
        box2.getItems().setAll(courseList);
    }

    @FXML public void okOnClick(){
        if(counter==1) {
            teacherService.addTeacher(new Teacher(text1.getText(), text2.getText(),
                    Lists.getPositionList().get(box2.getSelectionModel().getSelectedIndex()),
                    Lists.getDepartmentService().getDepartments().get(box1.getSelectionModel().getSelectedIndex())));
        }
        else{
            groupService.addGroupe(new Group(text1.getText(), Integer.parseInt(text2.getText()),Lists.getCourseService().getCourses().get(box2.getSelectionModel().getSelectedIndex())));
        }
        clear();
        if(counter==1) {
            Message.questionOnClick(addTeacherGroup, "Додавання викладача", "Додати ще одного викладлача?");
        }
        else{
            Message.questionOnClick(addTeacherGroup,"Додавання групи", "Додати ще одну групу?");
        }
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addTeacherGroup.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
    }
}
