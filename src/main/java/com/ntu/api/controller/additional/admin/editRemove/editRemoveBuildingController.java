package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.service.serviceInterface.BuildingServiceInt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class editRemoveBuildingController {
    @FXML private AnchorPane editRemoveBuilding;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField  text1;
    @FXML private TextField text2;
    @FXML private ComboBox <String> box;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;
    private static ObservableList<String> buildingList;
    Building building;
    ApplicationContext context = new ClassPathXmlApplicationContext
            ("com/ntu/api/spring/database/config.xml");
    BuildingServiceInt buildingService = context.getBean(BuildingServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveBuildingController.bool = bool;
    }

    @FXML public void initialize(){
        label0.setText("Корпус");
        label1.setText("Назва");
        label2.setText("Адреса");
        if(bool){
            button1.textProperty().set("Зберегти корпус");
        }
        else{
            button1.textProperty().set("Видалити корпус");
        }
        buildingList = FXCollections.observableArrayList();
        buildingList.addAll(Lists.getBuildingList());
        box.setEditable(false);
        box.getItems().setAll(buildingList);
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveBuilding.getScene().getWindow();
        dlg.close();
    }
    @FXML public void okOnClick(){
        if (bool){
            building.setBuildingName(text1.getText());
            building.setBuildingAdress(text2.getText());
            buildingService.updateBuilding(building);
        }
        else{
            buildingService.deleteBuilding(building);
        }
        cancelOnClick();
    }
    @FXML public void chooseOnClick(){
        building = buildingService.getBuildingList().get(box.getSelectionModel().getSelectedIndex());
        text1.setText(building.getBuildingName());
        text2.setText(building.getBuildingAdress());
    }
}
