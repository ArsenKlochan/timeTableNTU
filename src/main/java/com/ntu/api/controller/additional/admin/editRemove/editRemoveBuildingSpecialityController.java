package com.ntu.api.controller.additional.admin.editRemove;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.service.serviceInterface.BuildingServiceInt;
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

public class editRemoveBuildingSpecialityController {
    @FXML private AnchorPane editRemoveBuildingSpeciality;
    @FXML private Label label0;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField  text1;
    @FXML private TextField text2;
    @FXML private ComboBox <String> box;
    @FXML private Button button1;
    @FXML private Button button2;
    private static Boolean bool;
    private static Boolean enterBool;
    private static  int flag;
    private static ObservableList<String> objectList;
    Building building;
    Speciality speciality;
    ApplicationContext context = new ClassPathXmlApplicationContext
            ("com/ntu/api/spring/database/config.xml");
    BuildingServiceInt buildingService = context.getBean(BuildingServiceInt.class);
    SpecialityServiceInt specialityService = context.getBean(SpecialityServiceInt.class);

    public static Boolean getBool() {
        return bool;
    }
    public static void setBool(Boolean bool) {
        editRemoveBuildingSpecialityController.bool = bool;
    }
    public static void setFlag(int flag) {
        editRemoveBuildingSpecialityController.flag = flag;
    }

    @FXML public void initialize(){
        enterBool = true;
        objectList = FXCollections.observableArrayList();
        if(flag==1) {
            label0.setText("Корпус");
            label1.setText("Назва");
            label2.setText("Адреса");
            if (bool) {
                button1.textProperty().set("Зберегти корпус");
            }
            else {
                button1.textProperty().set("Видалити корпус");
                windowsDisabled();
            }
            objectList.addAll(Lists.getBuildingList());
            box.setEditable(false);
        }
        else if(flag==2){
            label0.setText("Спеціальність");
            label1.setText("Код спеціальності");
            label2.setText("Назва спеціальності");
            if (bool){
                button1.textProperty().set("Зберегти спеціальність");
            }
            else{
                button1.textProperty().set("Видалити спеціальність");
                windowsDisabled();
            }
            objectList.addAll(Lists.getSpecialityList());
        }
        box.setEditable(false);
        box.getItems().setAll(objectList);
        text1.clear();
        text2.clear();
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage) editRemoveBuildingSpeciality.getScene().getWindow();
        dlg.close();
    }
    @FXML public void okOnClick() {
        try {
            enterBool = false;
            if (flag == 1) {
                if (bool) {
                    building.setBuildingName(text1.getText());
                    building.setBuildingAdress(text2.getText());
                    buildingService.updateBuilding(building);
                } else {
                    buildingService.deleteBuilding(building);
                }
            } else if (flag == 2) {
                if (bool) {
                    speciality.setSpecialityCode(text1.getText());
                    speciality.setSpecialityName(text2.getText());
                    specialityService.updateSpeciality(speciality);
                } else {
                    specialityService.deleteSpeciality(speciality);
                }
            }
            BoxCleaner.boxClear(box);
            initialize();
            if (flag == 1) {
                if (bool) {
                    Message.questionOnClick(editRemoveBuildingSpeciality, "Редагування корпусу", "Редагувати ще один корпус?");
                } else {
                    Message.questionOnClick(editRemoveBuildingSpeciality, "Видалення корпусу", "Видалити ще один корпус?");
                }
            } else if (flag == 2) {
                if (bool) {
                    Message.questionOnClick(editRemoveBuildingSpeciality, "Редагування спеціальності", "Редагувати ще одну спеціальність?");
                } else {
                    Message.questionOnClick(editRemoveBuildingSpeciality, "Видалення спеціальності", "Видалити ще одну спеціальність?");
                }
            }
        }
        catch (DataIntegrityViolationException e){
            Message.errorCatch(editRemoveBuildingSpeciality, "Помилка видалення", "Об'єкт, який Ви намагаєтесь видалити містить прив'язані записи в базі даних. Для видалення даного об'єкту видаліть або відредагуйте всі повязані з ним записи в базі даних");
        }
    }
    @FXML public void chooseOnClick(){
        if(enterBool) {
            if (flag == 2) {
                speciality = specialityService.getSpecialities().get(box.getSelectionModel().getSelectedIndex());
                text1.setText(speciality.getSpecialityCode());
                text2.setText(speciality.getSpecialityName());
            } else if (flag == 1) {
                building = buildingService.getBuildingList().get(box.getSelectionModel().getSelectedIndex());
                text1.setText(building.getBuildingName());
                text2.setText(building.getBuildingAdress());
            }
        }
    }

    private void windowsDisabled(){
        text1.setDisable(true);
        text2.setDisable(true);
    }
}
