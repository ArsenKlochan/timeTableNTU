package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.entity.enums.LessonType;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
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

public class editRemoveLessonSpecialityController {
    @FXML private AnchorPane editRemoveLessonSpeciality;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;
    private static int flag;

    private ObservableList<String> objectList;
    private ObservableList<String> parameterOneList;
    private ObservableList<String> parameterTwoList;

    Lesson lesson;
    Speciality speciality;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    LessonServiceInt lessonService = context.getBean(LessonServiceInt.class);
    SpecialityServiceInt specialityService = context.getBean(SpecialityServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveLessonSpecialityController.bool = bool;
    }
    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        editRemoveLessonSpecialityController.flag = flag;
    }

    @FXML public void initialize(){
        objectList = FXCollections.observableArrayList();
        parameterOneList = FXCollections.observableArrayList();
        parameterTwoList = FXCollections.observableArrayList();
        if(flag==1){
            label0.setText("Заняття");
            label1.setText("Назва заняття");
            label2.setText("Тип заняття");
            label3.setText("Дисципліна");
            if(bool){
                button1.textProperty().set("Зберегти заняття");
            }
            else{
                button1.textProperty().set("Видалити заняття");
                box1.setDisable(true);
                box2.setDisable(true);
            }
            objectList.addAll(Lists.getLessonList());
            parameterOneList.addAll(Lists.getLessonTypeList());
            parameterTwoList.addAll(Lists.getSubjectsList());
        }
        else{
            label0.setText("Спеціальність");
            label1.setText("Назва спеціальності");
            label2.setText("Освітня програма");
            label3.setText("Кафедра");
            if(bool){
                button1.textProperty().set("Зберегти спеціальність");
            }
            else{
                button1.textProperty().set("Видалити спеціальність");
                box1.setDisable(true);
                box2.setDisable(true);
            }
            objectList.addAll(Lists.getSpecialityList());
            parameterOneList.addAll(Lists.getCurriculumList());
            parameterTwoList.addAll(Lists.getDepartmentList());
        }
    }
    @FXML public void okOnClick(){
        if(flag==1){
            if(bool){
                lesson.setLessonName(text1.getText());
                lessonService.updateLesson(lesson);
            }
            else{
                lessonService.deleteLesson(lesson);
            }
        }
        else{
            if(bool){
                speciality.setSpecialityName(text1.getText());
                specialityService.updateSpeciality(speciality);
            }
            else{
                specialityService.deleteSpeciality(speciality);
            }
        }
        cancelOnClick();
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveLessonSpeciality.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if(flag==1){
            lesson.setLessonType(LessonType.values()[box1.getSelectionModel().getSelectedIndex()]);
        }
        else {
            speciality.setCurriculum(Lists.getCurriculumService().getCurriculums().get(box1.getSelectionModel().getSelectedIndex()));
        }
    }
    @FXML public void box2OnClick(){
        if(flag==1){
            lesson.setSubject(Lists.getSubjectService().getSubjectList().get(box2.getSelectionModel().getSelectedIndex()));
        }
        else {
            speciality.setDepartment(Lists.getDepartmentService().getDepartments().get(box2.getSelectionModel().getSelectedIndex()));
        }
    }
    @FXML public void chooseOnClick(){
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        List<String> parameters = new ArrayList<>();
        if(flag==1){
            lesson = lessonService.getLessons().get(box.getSelectionModel().getSelectedIndex());
            parameters = lessonService.getParametersInString(lesson);
        }
        else{
            speciality = specialityService.getSpecialities().get(box.getSelectionModel().getSelectedIndex());
            parameters = specialityService.getParametersInString(speciality);
        }
        text1.setText(parameters.get(0));
        box1.promptTextProperty().set(parameters.get(1));
        box2.promptTextProperty().set(parameters.get(2));
    }
}
