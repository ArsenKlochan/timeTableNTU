package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.entity.enums.LessonType;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
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
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

public class editRemoveLessonCurriculumController {
    @FXML private AnchorPane editRemoveLessonCurriculum;
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
    private static int flag;
    private static Boolean bool;
    private static Boolean editBool;
    private static Boolean enterBool;
    List<Curriculum> curriculums;
    List<Lesson> lessons;

    private ObservableList<String> objectList;
    private ObservableList<String> parameterOneList;
    private ObservableList<String> parameterTwoList;

    Lesson lesson;
    Curriculum curriculum;
    Department department;
    Speciality speciality;
    ApplicationContext context = new ClassPathXmlApplicationContext("com/ntu/api/spring/database/config.xml");
    LessonServiceInt lessonService = context.getBean(LessonServiceInt.class);
    CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveLessonCurriculumController.bool = bool;
    }
    public static int getFlag() {
        return flag;
    }
    public static void setFlag(int flag) {
        editRemoveLessonCurriculumController.flag = flag;
    }

    @FXML public void initialize(){
        editBool = false;
        enterBool = true;
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
                box1.setDisable(false);
                box2.setDisable(false);
                text1.setDisable(true);
            }
            objectList.setAll(Lists.getLessonList());
            parameterOneList.setAll(Lists.getLessonTypeList());
            parameterTwoList.setAll(Lists.getSubjectsList());
            lessons = Lists.getLessonService().getLessons();
        }
        else{
            label0.setText("Освітня програма");
            label1.setText("Назва освітньої програми");
            label2.setText("Спеціальність");
            label3.setText("Кафедра");
            if(bool){
                button1.textProperty().set("Зберегти освітню програму");
            }
            else{
                button1.textProperty().set("Видалити освітню програму");
                box1.setDisable(false);
                box2.setDisable(false);
                text1.setDisable(true);
            }
            objectList.setAll(Lists.getCurriculumList());
            parameterOneList.setAll(Lists.getSpecialityList());
            parameterTwoList.setAll(Lists.getDepartmentList());
            curriculums = Lists.getCurriculumService().getCurriculums();
        }
        box.setEditable(false);
        box1.setEditable(false);
        box2.setEditable(false);

        box.getItems().setAll(objectList);
        box1.getItems().setAll(parameterOneList);
        box2.getItems().setAll(parameterTwoList);
    }
    @FXML public void okOnClick() {
        try {
            enterBool = false;
            if (flag == 1) {
                if (bool) {
                    lesson.setLessonName(text1.getText());
                    lessonService.updateLesson(lesson);
                } else {
                    lessonService.deleteLesson(lesson);
                }
            } else {
                if (bool) {
                    curriculum.setCurriculumName(text1.getText());
                    curriculumService.updateCurriculum(curriculum);
                } else {
                    curriculumService.deleteCurriculum(curriculum);
                }
            }
            clear();
            initialize();
            if (flag == 1) {
                if (bool) {
                    Message.questionOnClick(editRemoveLessonCurriculum, "Редагувати заняття", "Редагувати ще одне заняття?");
                } else {
                    Message.questionOnClick(editRemoveLessonCurriculum, "Видалити заняття", "Видалити ще одне заняття?");
                }
            } else {
                if (bool) {
                    Message.questionOnClick(editRemoveLessonCurriculum, "Редагування освітньої програми", "Редагувати ще одну освітню програму?");
                } else {
                    Message.questionOnClick(editRemoveLessonCurriculum, "Видалення освітньої програми", "Видалити ще одну освітню програму?");
                }
            }
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(editRemoveLessonCurriculum, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveLessonCurriculum.getScene().getWindow();
        dlg.close();
    }
    @FXML public void box1OnClick(){
        if(enterBool) {
            if (flag == 1) {
                LessonType lessonType = LessonType.values()[box1.getSelectionModel().getSelectedIndex()];
                if(editBool) {
                    lesson.setLessonType(lessonType);
                }
                else{
                    BoxCleaner.boxTwoClear(box, box2);
                    objectList.setAll(Lists.getLessonService().getLessonsOnType(lessonType));
                    lessons = Lists.getLessonService().getLessonsOnTypeList(lessonType);
                }
            } else {
                Speciality speciality = Lists.getSpecialityService().getSpecialities().get(box1.getSelectionModel().getSelectedIndex());
                if(editBool){
                    curriculum.setSpeciality(speciality);
                }
                else{
                    BoxCleaner.boxTwoClear(box, box2);
                    objectList.setAll(Lists.getCurriculumService().getCurriculumsBySpecialityNames(speciality));
                    curriculums = Lists.getCurriculumService().getCurriculumsBySpeciality(speciality);
                }
            }
            box.getItems().setAll(objectList);
        }
    }

    @FXML public void box2OnClick(){
        if(enterBool) {
            if (flag == 1) {
                Subjects subjects = Lists.getSubjectService().getSubjectList().get(box2.getSelectionModel().getSelectedIndex());
                if(editBool) {
                    lesson.setSubject(subjects);
                }
                else{
                    BoxCleaner.boxTwoClear(box, box1);
                    objectList.setAll(Lists.getLessonService().getLessonsOnSubject(subjects));
                    lessons = Lists.getLessonService().getLessonsOnSubjectList(subjects);
                }
            } else {
                Department department = Lists.getDepartmentService().getDepartments().get(box2.getSelectionModel().getSelectedIndex());
                if(editBool){
                    curriculum.setDepartment(department);
                }
                else{
                    BoxCleaner.boxTwoClear(box, box1);
                    objectList.setAll(Lists.getCurriculumService().getCurriculumsByDepartmentNames(department));
                    curriculums = Lists.getCurriculumService().getCurriculumByDepartment(department);
                }
            }
            box.getItems().setAll(objectList);
        }
    }
    @FXML public void chooseOnClick(){
        if(enterBool) {
            enterBool = false;
            if (!editBool && !bool) {
                box1.setDisable(true);
                box2.setDisable(true);
            }
            editBool = true;
            List<String> parameters;
            if (flag == 1) {
                lesson = lessons.get(box.getSelectionModel().getSelectedIndex());
                parameters = lessonService.getParametersInString(lesson);
            } else {
                curriculum = curriculums.get(box.getSelectionModel().getSelectedIndex());
                parameters = curriculumService.getParametersInString(curriculum);
            }
            text1.setText(parameters.get(0));
            box1.setValue(parameters.get(2));
            box2.setValue(parameters.get(1));
            enterBool = true;
        }
    }
    private void clear(){
        text1.clear();
        BoxCleaner.boxClear(box);
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
    }
}
