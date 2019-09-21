package com.ntu.api.controller.additional;

import com.ntu.api.TimeTableNtuApp;
import com.ntu.api.domain.database.entity.User;
import com.ntu.api.domain.database.service.serviceInterface.UserServiceInt;
import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RegistrationController {
    @FXML
    private AnchorPane dlgRegistration;
    @FXML
    TextField fldLogin;
    @FXML
    PasswordField fldPassword;
    @FXML
    PasswordField fldConfirm;
    public void registrationOnClick() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "com/ntu/api/spring/database/config.xml");
        UserServiceInt userService = context.getBean(UserServiceInt.class);

        if (fldLogin.getText().equals("") || fldPassword.getText().equals("") || fldConfirm.getText().equals("")) {
            Message.errorCatch(dlgRegistration, "Error", "Заповнені не всі поля. Заповніть будь-ласка всі поля");
        } else {
            if (fldPassword.getText().equals(fldConfirm.getText())) {
                if (!userService.addUserComplite(new User(fldLogin.getText(), fldPassword.getText()))) {
                    Message.errorCatch(dlgRegistration, "Error", "Користувач з таким логіном вже існує");
                } else {
                    closeOnClick();
                }
            } else {
                Message.errorCatch(dlgRegistration, "Error", "Пароль не співпадає з підтвердженням паролю");
            }
            cleanField();
        }
    }

    public void closeOnClick(){
        Stage dlg = (Stage) dlgRegistration.getScene().getWindow();
        dlg.close();
    }

    private void cleanField(){
        fldConfirm.clear();
        fldPassword.clear();
        fldLogin.clear();
    }
}
