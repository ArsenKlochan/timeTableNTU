package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
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

import java.util.List;

@Transactional
public class editRemoveClassRoomController {
    @FXML private AnchorPane editRemoveClassRoom;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private ComboBox<String> box;
    @FXML private ComboBox<String> box1;
    @FXML private ComboBox<String> box2;
    @FXML private ComboBox<String> box3;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;

    private static ObservableList<String> classRoomList;
    private static ObservableList<String> typeList;
    private static ObservableList<String> buildingList;
    private static ObservableList<String> departmentList;

    ClassRoom classRoom;
    ApplicationContext context = new ClassPathXmlApplicationContext
            ("com/ntu/api/spring/database/config.xml");
    ClassRoomServiceInt classRoomService = context.getBean(ClassRoomServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveClassRoomController.bool = bool;
    }

    @FXML public void initialize(){
        label0.setText("Аудиторії");
        label1.setText("Назва аудиторії");
        label2.setText("Кількість робочих місць");
        label3.setText("Тип аудиторії");
        label4.setText("Корпус");
        label5.setText("Кафедра");
        if(bool){
            button1.textProperty().set("Зберегти аудиторію");
        }
        else{
            button1.textProperty().set("Видалити аудиторію");
            box1.setDisable(true);
            box2.setDisable(true);
            box3.setDisable(true);
        }
        classRoomList = FXCollections.observableArrayList();
        typeList = FXCollections.observableArrayList();
        buildingList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        classRoomList.addAll(Lists.getClassRoomList());
        typeList.addAll(Lists.getClassRoomTypeList());
        buildingList.addAll(Lists.getBuildingList());
        departmentList.addAll(Lists.getDepartmentList());

        box1.setEditable(false);
        box2.setEditable(false);
        box3.setEditable(false);
        box.setEditable(false);

        box1.getItems().setAll(typeList);
        box2.getItems().setAll(buildingList);
        box3.getItems().setAll(departmentList);
        box.getItems().setAll(classRoomList);
    }

    @FXML public void okOnClick(){
        if(bool){
            classRoom.setClassRoomName(text1.getText());
            classRoom.setClassRoomSize(Integer.parseInt(text2.getText()));
            classRoomService.updateClassRoom(classRoom);
        }
        else{
            classRoomService.deleteClassRoom(classRoom);
        }
        cancelOnClick();
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveClassRoom.getScene().getWindow();
        dlg.close();
    }

    @FXML public void box1OnChoose(){
            classRoom.setType(ClassRoomTypes.valueOf(box1.promptTextProperty().get()));
    }
    @FXML public void box2OnChoose(){
        classRoom.setBuilding(Lists.getBuildingService().getBuildingList().get(box2.getSelectionModel().getSelectedIndex()));
    }
    @FXML public void box3OnChoose(){
        classRoom.setDepartment(Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex()));
    }

    @FXML public void boxchooseOnClick(){
        BoxCleaner.boxClear(box1);
        BoxCleaner.boxClear(box2);
        BoxCleaner.boxClear(box3);
        classRoom = classRoomService.getClassRoomList().get(box.getSelectionModel().getSelectedIndex());
        List<String> parameters = classRoomService.getParametersInString(classRoom);
        text1.setText(parameters.get(0));
        text2.setText(parameters.get(1));
        box1.promptTextProperty().set(parameters.get(2));
        box2.promptTextProperty().set(parameters.get(3));
        box3.promptTextProperty().set(parameters.get(4));
    }
}
