package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.entity.enums.Position;
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
import org.springframework.dao.DataIntegrityViolationException;

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
    private Course course;
    private Curriculum curriculum;
    private static Boolean editBool;
    private Department department;
    private Position position;
    private List<Teacher> teachers;
    private List<Group> groups;
    private List<Course> courses;

    private static ApplicationContext context = new ClassPathXmlApplicationContext(
            "/com/ntu/api/spring/database/config.xml");
    private static TeacherServiceInt teacherService = context.getBean(TeacherServiceInt.class);
    private static CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    private static GroupServiceInt groupService = context.getBean(GroupServiceInt.class);
    private static int flag;
    private static boolean bool;
    private static boolean enterBool;

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
        clear();
        editBool = false;
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
                text1.setDisable(true);
                text2.setDisable(true);
                box1.setDisable(false);
                box2.setDisable(false);
            }
            objectList.setAll(Lists.getTeacherList());
            parameterOneList.setAll(Lists.getDepartmentList());
            parameterTwoList.setAll(Lists.getPositionList());
            teachers = Lists.getTeacherService().getTeachers();
        }
        else{
            label0.setText("Група");
            label1.setText("Назва групи");
            label2.setText("Кількість студентів");
            label3.setText("Освітня програма");
            label4.setText("Курс");
            if(bool) {
                button1.textProperty().set("Зберегти групу");
            }
            else {
                button1.textProperty().set("Видалити групу");
                text1.setDisable(true);
                text2.setDisable(true);
                box1.setDisable(false);
                box2.setDisable(false);
            }
            courses = Lists.getCourseService().getCourses();
            objectList.setAll(Lists.getGroupeList());
            parameterOneList.setAll(Lists.getCurriculumList());
            parameterTwoList.setAll(Lists.getCourseList());
            groups = Lists.getGroupService().getGroups();
        }
            box.setEditable(false);
            box1.setEditable(false);
            box2.setEditable(false);
            box.getItems().setAll(objectList);
            box1.getItems().setAll(parameterOneList);
            box2.getItems().setAll(parameterTwoList);
        enterBool = true;
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
                teacher = teachers.get(box.getSelectionModel().getSelectedIndex());
                parameters = teacherService.getParametersInString(teacher);
            } else {
                group = groups.get(box.getSelectionModel().getSelectedIndex());
                parameters = groupService.getParametersInString(group);
            }
            text1.setText(parameters.get(0));
            text2.setText(parameters.get(1));
            box1.setValue(parameters.get(2));
            box2.setValue(parameters.get(3));
            enterBool =  true;
        }
    }

    @FXML public void box1OnClick() {
        if (enterBool) {
            if (flag == 1) {
                department = Lists.getDepartmentService().getDepartments().get(box1.getSelectionModel().getSelectedIndex());
                if (editBool) {
                    teacher.setDepartment(department);
                } else {
                    BoxCleaner.boxClear(box2);
                    objectList.clear();
                    objectList.setAll(teacherService.getTeachersOnDepartments(department));
                    teachers = teacherService.getTeacherOnDepartmentList(department);
                    box.getItems().setAll(objectList);
                }
            } else {
                curriculum = curriculumService.getCurriculums().get(box1.getSelectionModel().getSelectedIndex());
                if(!editBool){
                    BoxCleaner.boxTwoClear(box,box2);
                    objectList.clear();
                    parameterTwoList.setAll(Lists.getCourseService().getCourseOnCurriculuminString(curriculum));
                    courses = Lists.getCourseService().getCourseOnCurriculumList(curriculum);
                    box2.getItems().setAll(parameterTwoList);
                    box.setDisable(true);
                }
            }
        }
    }

    @FXML public void box2OnClick(){
        if(enterBool) {
            if (flag == 1) {
                position = Position.values()[box2.getSelectionModel().getSelectedIndex()];
                if (editBool) {
                    teacher.setTeacherPosition(position.name());
                } else {
                    BoxCleaner.boxClear(box1);
                    objectList.clear();
                    objectList.setAll(teacherService.getTeachersByPosition(position));
                    teachers = teacherService.getTeacherByPositionList(position);
                    box.getItems().setAll(objectList);
                }
            } else {
                course = courses.get(box2.getSelectionModel().getSelectedIndex());
                if(editBool){
                    group.setCourse(course);
                }
                else{
                    BoxCleaner.boxClear(box1);
                    box.setDisable(false);
                    objectList.clear();
                    objectList.setAll(groupService.getGroupOnCourse(course));
                    groups = groupService.getGroupOnCourseList(course);
                    box.getItems().setAll(objectList);
                }
            }
        }
    }

    @FXML public void okOnClick() {
        try {
            enterBool = false;
            if (flag == 1) {
                if (bool) {
                    teacher.setTeacherSurname(text1.getText());
                    teacher.setTeacherName(text2.getText());
                    teacherService.updateTeacher(teacher);
                } else {
                    teacherService.deleteTeacher(teacher);
                }
            } else {
                if (bool) {
                    group.setGroupName(text1.getText());
                    group.setStudentsNumber(Integer.parseInt(text2.getText()));
                    groupService.updateGroupe(group);
                } else {
                    groupService.deleteGroupe(group);
                }
            }
            clear();
            initialize();
            if (flag == 1) {
                if (bool) {
                    Message.questionOnClick(editRemoveTeacherGroup, "Редагування викладача", "Редагувати ще одного викладлача?");
                } else {
                    Message.questionOnClick(editRemoveTeacherGroup, "Видалення викладача", "Видалити ще одного викладлача?");
                }
            } else {
                if (bool) {
                    Message.questionOnClick(editRemoveTeacherGroup, "Редагування групи", "Редагувати ще одну групу?");
                } else {
                    Message.questionOnClick(editRemoveTeacherGroup, "Видалення групи", "Видалити ще одну групу?");
                }
            }
        } catch (NumberFormatException e) {
            Message.errorCatch(editRemoveTeacherGroup, "Помилка введення", "Перевірте правильність введення числових даних");
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(editRemoveTeacherGroup, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
        }
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(editRemoveTeacherGroup.getScene().getWindow());
        dlg.close();
    }
    private void clear(){
        BoxCleaner.boxTwoClear(box, box1);
        BoxCleaner.boxClear(box2);
        text1.clear();
        text2.clear();
    }
    private void boxClear(ComboBox<String> box){
        box.promptTextProperty().setValue("");
        box.setValue("");
    }

}
