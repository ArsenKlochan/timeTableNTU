package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.enums.LessonType;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
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

public class addLessonCurriculumController {
    @FXML private AnchorPane addLessonSpeciality;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static int count;
    private static ObservableList<String> lessonTypeList;
    private static ObservableList<String> subjectList;
    private static ObservableList<String> specialityList;
    private static ObservableList<String> departmentList;

    public static void setCount(int count) {
        addLessonCurriculumController.count = count;
    }

    @FXML public void initialize(){
        if(count==1){
            label1.setText("Назва заняття");
            label2.setText("Тип заняття");
            label3.setText("Дисципліна");
            button1.textProperty().set("Додати заняття");

            lessonTypeList = FXCollections.observableArrayList();
            subjectList = FXCollections.observableArrayList();

            lessonTypeList.addAll(Lists.getLessonTypeList());
            subjectList.addAll(Lists.getSubjectsList());

            box1.setEditable(false);
            box2.setEditable(false);

            box1.getItems().setAll(lessonTypeList);
            box2.getItems().setAll(subjectList);
        }
        if(count==2){
            label1.setText("Назва освітньої програми");
            label2.setText("Спеціальність");
            label3.setText("Кафедра");
            button1.textProperty().set("Додати освітню програму");

            specialityList = FXCollections.observableArrayList();
            departmentList = FXCollections.observableArrayList();

            specialityList.addAll(Lists.getSpecialityList());
            departmentList.addAll(Lists.getDepartmentList());

            box1.setEditable(false);
            box2.setEditable(false);

            box1.getItems().setAll(specialityList);
            box2.getItems().setAll(departmentList);
        }
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        if(count == 1) {
            LessonServiceInt lessonService = context.getBean(LessonServiceInt.class);
            lessonService.addLesson(new Lesson(text1.getText(),
                    LessonType.valueOf(box1.getSelectionModel().getSelectedItem()),
                    Lists.getSubjectService().getSubjectList().get(box2.getSelectionModel().getSelectedIndex())));
        }
        if(count==2){
            CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
            curriculumService.addCurriculum(new Curriculum(text1.getText(),
                    Lists.getSpecialityService().getSpecialities().get(box1.getSelectionModel().getSelectedIndex()),
                    Lists.getDepartmentService().getDepartments().get(box2.getSelectionModel().getSelectedIndex())));
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addLessonSpeciality.getScene().getWindow());
        dlg.close();
    }
}
