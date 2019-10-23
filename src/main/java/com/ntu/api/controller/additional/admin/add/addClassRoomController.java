package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.controller.Filter;
import com.ntu.api.controller.additional.ErrorController;
import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
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

import java.util.ArrayList;
import java.util.List;

public class addClassRoomController {
    @FXML private AnchorPane addClassRoom;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private ComboBox<String> box3;
    @FXML private Button button1;
    @FXML private Button button2;

    private static ObservableList<String> typeList;
    private static ObservableList<String> buildingList;
    private static ObservableList<String> departmentList;
    private static ArrayList<Department> departments;
    private static ArrayList<Building> buildings;

    @FXML public void initialize(){
        button1.textProperty().set("Додати аудиторію");
        label1.setText("Назва аудиторії");
        label2.setText("Кількість робочих місць");
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

        box1.getItems().setAll(typeList);
        box2.getItems().setAll(buildingList);
        box3.getItems().setAll(departmentList);
        buildings = Filter.filtere(buildingList,box2, Lists.getBuildingService().getBuildingList());
        departments = Filter.filtere(departmentList,box3, Lists.getDepartmentService().getDepartments());
    }

    @FXML public void okOnClick(){
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "com/ntu/api/spring/database/config.xml");
            ClassRoomServiceInt classRoomService = context.getBean(ClassRoomServiceInt.class);
            classRoomService.addClassRoom(new ClassRoom(text1.getText(), Integer.parseInt(text2.getText()),
                    ClassRoomTypes.valueOf(box1.getSelectionModel().getSelectedItem()),
                    buildings.get(box2.getSelectionModel().getSelectedIndex()),
                    departments.get(box3.getSelectionModel().getSelectedIndex())));
            clear();
            Message.questionOnClick(addClassRoom, "Додавання аудиторії", "Додати ще одну аудиторію?");
        }
        catch (NumberFormatException e){
            Message.errorCatch(addClassRoom, "Помилка введення", "Перевірте правильність введення числових даних");
        }
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addClassRoom.getScene().getWindow());
        dlg.close();
    }

    private void clear(){
        text1.clear();
        text2.clear();
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
    }
}

