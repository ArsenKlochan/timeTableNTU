package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.enums.LessonType;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
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

public class addLessonController {
    @FXML private AnchorPane addLesson;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private TextField text1;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static ObservableList<String> lessonTypeList;
    private static ObservableList<String> subjectList;

    @FXML public void initialize(){
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

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
            LessonServiceInt lessonService = context.getBean(LessonServiceInt.class);
            lessonService.addLesson(new Lesson(text1.getText(),
                    LessonType.valueOf(box1.getSelectionModel().getSelectedItem()),
                    Lists.getSubjectService().getSubjectList().get(box2.getSelectionModel().getSelectedIndex())));

        clear();
            Message.questionOnClick(addLesson, "Додавання заняття", "Додати ще одне заняття?");
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addLesson.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
    }
}
