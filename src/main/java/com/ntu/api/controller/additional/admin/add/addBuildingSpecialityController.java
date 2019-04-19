package com.ntu.api.controller.additional.admin.add;

import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.service.serviceInterface.BuildingServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class addBuildingSpecialityController {
    @FXML private AnchorPane addBuildingSpeciality;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private TextField text1;
    @FXML private TextField text2;
    @FXML private Button button1;
    @FXML private Button button2;
    private static int counter;

    public static void setCounter(int counter) {
        addBuildingSpecialityController.counter = counter;
    }

    @FXML public void initialize(){
        if(counter==1){
            label1.setText("Назва");
            label2.setText("Адреса");
            button1.textProperty().set("Додати Корпус");}
        if(counter==2){
            label1.setText("Код спеціальності");
            label2.setText("Назва спеціальності");
            button1.textProperty().set("Додати спеціальність");
        }
    }
    @FXML public void cancelOnClick(){
        Stage dlg = (Stage)(addBuildingSpeciality.getScene().getWindow());
        dlg.close();
    }

    @FXML public void okOnClick(){
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "com/ntu/api/spring/database/config.xml");
        if(counter==1){
            BuildingServiceInt buildingService = context.getBean(BuildingServiceInt.class);
            buildingService.addBuilding(new Building(text1.getText(), text2.getText()));
        }
        if(counter==2){
            SpecialityServiceInt specialityService = context.getBean(SpecialityServiceInt.class);
            specialityService.addSpeciality(new Speciality(text1.getText(), text2.getText()));
        }
        clear();
        if(counter==1) {
            Message.questionOnClick(addBuildingSpeciality, "Додавання корпусу", "Додати ще один корпус?");
        }
        else{
            Message.questionOnClick(addBuildingSpeciality,"Додавання спеціальності", "Додати ще одну спеціальність?");
        }
    }

    private void clear(){
        text1.clear();
        text2.clear();
    }
}
