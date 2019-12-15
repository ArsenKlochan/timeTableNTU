package com.ntu.api.controller.additional.admin.editRemove;

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
import org.apache.commons.codec.net.BCodec;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
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
    private static Boolean editBool;
    private List<ClassRoom> classRooms;
    private static Boolean enterBool;


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
        clear();
        editBool = false;
        enterBool = true;
        label0.setText("Аудиторія");
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
            box1.setDisable(false);
            text1.setDisable(true);
            text2.setDisable(true);
        }
        classRoomList = FXCollections.observableArrayList();
        typeList = FXCollections.observableArrayList();
        buildingList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        classRoomList.addAll(Lists.getClassRoomList());
        typeList.addAll(Lists.getClassRoomTypeList());
        buildingList.addAll(Lists.getBuildingList());
        departmentList.addAll(Lists.getDepartmentList());

        box1.getItems().setAll(typeList);
        box2.getItems().setAll(buildingList);
        box3.getItems().setAll(departmentList);
        box.getItems().setAll(classRoomList);
        classRooms = Lists.getClassRoomService().getClassRoomList();
    }

    @FXML public void okOnClick(){
        try {
            enterBool = false;
            if(bool){
                classRoom.setClassRoomName(text1.getText());
                classRoom.setClassRoomSize(Integer.parseInt(text2.getText()));
                classRoomService.updateClassRoom(classRoom);
            }
            else{
                classRoomService.deleteClassRoom(classRoom);
            }
            clear();
            initialize();
            box1.setDisable(false);
            box2.setDisable(false);
            box3.setDisable(false);
            box.setDisable(false);
            if(bool){
                Message.questionOnClick(editRemoveClassRoom,"Редагування аудиторії", "Редагувати ще одну аудиторію?");
            }
            else{
                Message.questionOnClick(editRemoveClassRoom,"Видалення аудиторії", "Видалити ще одну аудиторію?");
            }
        }
        catch (NumberFormatException e){
            Message.errorCatch(editRemoveClassRoom, "Помилка введення", "Перевірте правильність введення числових даних");
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(editRemoveClassRoom, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
        }
    }

    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveClassRoom.getScene().getWindow();
        dlg.close();
    }

    @FXML public void box1OnChoose(){
        if(enterBool) {
            if (editBool) {
                classRoom.setType(ClassRoomTypes.values()[box1.getSelectionModel().getSelectedIndex()]);
            } else {
                departmentList.clear();
                buildingList.clear();
                BoxCleaner.boxTwoClear(box2, box3);
                BoxCleaner.boxClear(box);
                classRoomList.setAll(classRoomService.getClassRoomsOnType(ClassRoomTypes.values()[box1.getSelectionModel().getSelectedIndex()]));
                classRooms = classRoomService.getClassRoomsOnTypeList(ClassRoomTypes.values()[box1.getSelectionModel().getSelectedIndex()]);
                box.getItems().setAll(classRoomList);
            }
        }
    }
    @FXML public void box2OnChoose(){
        if(enterBool) {
            if (editBool) {
                classRoom.setBuilding(Lists.getBuildingService().getBuildingList().get(box2.getSelectionModel().getSelectedIndex()));
            } else {
                departmentList.clear();
                BoxCleaner.boxTwoClear(box, box3);
                BoxCleaner.boxClear(box1);
                classRoomList.setAll(classRoomService.getClassRoomsOnBuildin(Lists.getBuildingService().getBuildingList().get(box2.getSelectionModel().getSelectedIndex())));
                classRooms = classRoomService.getClassRoomsOnBuildinList(Lists.getBuildingService().getBuildingList().get(box2.getSelectionModel().getSelectedIndex()));
                box.getItems().setAll(classRoomList);
            }
        }
    }
    @FXML public void box3OnChoose(){
        if(enterBool) {
            if (editBool) {
                classRoom.setDepartment(Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex()).getDepartmentName());
            } else {
                buildingList.clear();
                BoxCleaner.boxTwoClear(box, box2);
                BoxCleaner.boxClear(box1);
                classRoomList.setAll(classRoomService.getClassRoomsOnDepartments(Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex())));
                classRooms = classRoomService.getClassRoomsOnDepartmentsList(Lists.getDepartmentService().getDepartments().get(box3.getSelectionModel().getSelectedIndex()));
                box.getItems().setAll(classRoomList);
            }
        }
    }

    @FXML public void boxchooseOnClick(){
        if(enterBool) {
            if (!editBool && !bool) {
                box1.setDisable(true);
                box2.setDisable(true);
                box3.setDisable(true);
            }
            editBool = true;
            classRoom = classRooms.get(box.getSelectionModel().getSelectedIndex());
            List<String> parameters = classRoomService.getParametersInString(classRoom);
            text1.setText(parameters.get(0));
            text2.setText(parameters.get(1));
            box1.setValue(parameters.get(2));
            box2.setValue(parameters.get(3));
            box3.setValue(parameters.get(4));
        }
    }

    private void clear(){
        text1.clear();
        text2.clear();
        BoxCleaner.boxTwoClear(box, box1);
        BoxCleaner.boxTwoClear(box2, box3);
    }
}
