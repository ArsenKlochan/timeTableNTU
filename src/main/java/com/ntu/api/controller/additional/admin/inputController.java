package com.ntu.api.controller.additional.admin;

import com.ntu.api.controller.additional.admin.add.addBuildingController;
import com.ntu.api.controller.additional.admin.add.addCourseFacultyController;
import com.ntu.api.controller.additional.admin.add.addCurriculumDepartmentGroupController;
import com.ntu.api.controller.additional.admin.add.addLessonSpecialityController;
import com.ntu.api.controller.additional.admin.editRemove.editRemoveBuildingController;
import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class inputController {
    @FXML private AnchorPane adminDlgInput;

    @FXML public void okOnClick(){

    }

    @FXML public void addBuilding(){
        open("Додавання корпусу", "/com/ntu/api/javafx/model/additional/admin/add/addBuilding.fxml", "Помилка додавання корпусу");
    }
    @FXML public void addClassroom(){
        open("Додавання аудиторії", "/com/ntu/api/javafx/model/additional/admin/add/addClassRoom.fxml", "Помилка додавання аудиторії");
    }
    @FXML public void addCourse(){
        addCourseFacultyController.setCounter(1);
        open("Додавання курсу", "/com/ntu/api/javafx/model/additional/admin/add/addCourseFaculty.fxml", "Помилка додавання курсу");
    }
    @FXML public void addCurriculum(){
        addCurriculumDepartmentGroupController.setCounter(1);
        open("Додавання освітньої програми", "/com/ntu/api/javafx/model/additional/admin/add/addCurriculumDepartmentGroup.fxml", "Помилка додавання освітньої програми");
    }
    @FXML public void addDepartment(){
        addCurriculumDepartmentGroupController.setCounter(2);
        open("Додавання кафедри", "/com/ntu/api/javafx/model/additional/admin/add/addCurriculumDepartmentGroup.fxml", "Помилка додавання кафедри");
    }
    @FXML public void addGroup(){
        addCurriculumDepartmentGroupController.setCounter(3);
        open("Додавання групи", "/com/ntu/api/javafx/model/additional/admin/add/addCurriculumDepartmentGroup.fxml", "Помилка додавання групи");
    }
    @FXML public void addLesson(){
        addLessonSpecialityController.setCount(1);
        open("Додавання заняття", "/com/ntu/api/javafx/model/additional/admin/add/addLessonSpeciality.fxml", "Помилка додавання заняття");
    }
    @FXML public void addTeacher(){
        open("Додати викладача","/com/ntu/api/javafx/model/additional/admin/add/addTeacher.fxml", "Помилка додавання викладача");
    }
    @FXML public void addSpesiality(){
        addLessonSpecialityController.setCount(2);
        open("Додавання спеціальності", "/com/ntu/api/javafx/model/additional/admin/add/addLessonSpeciality.fxml", "Помилка додавання спеціальності");
    }
    @FXML public void addSubject(){
        open("Додавання дисципліни", "/com/ntu/api/javafx/model/additional/admin/add/addSubject.fxml", "Помилка додавання дисципліни");
//        add("Додавання дисципліни", "/com/ntu/api/javafx/model/additional/admin/add/addCourseFaculty.fxml", "Помилка додавання дисципліни");
    }
    @FXML public void addFaculty(){
        addCourseFacultyController.setCounter(2);
        open("Додавання факультету", "/com/ntu/api/javafx/model/additional/admin/add/addCourseFaculty.fxml", "Помилка додавання факультету");
    }

    private void open(String title, String resource, String message){
        Stage add = new Stage();
        add.setTitle(title);
        add.setResizable(false);

        AnchorPane addPane = null;
        try {
            addPane = FXMLLoader.load(getClass().getResource(resource));
            add.initOwner(adminDlgInput.getScene().getWindow());
            add.initModality(Modality.WINDOW_MODAL);
            add.setScene(new Scene(addPane));
            add.show();
        } catch (IOException e) {
            Message.errorCatch(adminDlgInput, "Error", message);
        }
    }

    @FXML public void editBuilding(){
        editRemoveBuildingController.setBool(true);
        open("Редагування корпусу","/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuilding.fxml","Помилка редагування корпусу");
    }

    @FXML public void editClassroom(){}
    @FXML public void editCourse(){}
    @FXML public void editCurriculum(){}
    @FXML public void editDepartment(){}
    @FXML public void editGroup(){}
    @FXML public void editLesson(){}
    @FXML public void editTeacher(){}
    @FXML public void editSpesiality(){}
    @FXML public void editSubject(){}
    @FXML public void editFaculty(){}

    @FXML public void deleteBuilding(){
        editRemoveBuildingController.setBool(false);
        open("Редагування корпусу","/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuilding.fxml","Помилка редагування корпусу");
    }
    @FXML public void deleteClassroom(){}
    @FXML public void deleteCourse(){}
    @FXML public void deleteCurriculum(){}
    @FXML public void deleteDepartment(){}
    @FXML public void deleteGroup(){}
    @FXML public void deleteLesson(){}
    @FXML public void deleteTeacher(){}
    @FXML public void deleteSpesiality(){}
    @FXML public void deleteSubject(){}
    @FXML public void deleteFaculty(){}


}
