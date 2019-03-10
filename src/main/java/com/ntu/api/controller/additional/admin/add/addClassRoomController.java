package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
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

public class addClassRoomController {
    @FXML AnchorPane addClassRoom;
    @FXML Label label1;
    @FXML Label label2;
    @FXML Label label3;
    @FXML Label label4;
    @FXML Label label5;
    @FXML TextField text1;
    @FXML TextField text2;
    @FXML ComboBox<String> box1;
    @FXML ComboBox<String> box2;
    @FXML ComboBox<String> box3;
    @FXML Button button1;
    @FXML Button button2;

    private static ObservableList<String> typeList;
    private static ObservableList<String> buildingList;
    private static ObservableList<String> departmentList;

    @FXML public void initialize(){
        button1.textProperty().set("Додати аудиторію");
        label1.setText("Назва аудиторії");
        label2.setText("Опис аудиторії");
        label3.setText("Тип аудиторії");
        label4.setText("Корпус");
        label5.setText("Кафедра");

        typeList = FXCollections.observableArrayList();
        buildingList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        typeList.addAll(Lists.getClassRoomTypeList());
        buildingList.addAll(Lists.getBuildingList());
        departmentList.addAll(Lists.getDepartmentList());

        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);

        box1.getItems().setAll(typeList);
        box2.getItems().setAll(buildingList);
        box3.getItems().setAll(departmentList);
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        ClassRoomServiceInt classRoomService = context.getBean(ClassRoomServiceInt.class);
        classRoomService.addClassRoom(new ClassRoom(text1.getText(), text2.getText(),
                ClassRoomTypes.valueOf(box1.getSelectionModel().getSelectedItem()),
                Lists.getBuildingService().getBuildingList().get(box2.getSelectionModel().getSelectedIndex()),
                Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex())));
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addClassRoom.getScene().getWindow());
        dlg.close();
    }
}
