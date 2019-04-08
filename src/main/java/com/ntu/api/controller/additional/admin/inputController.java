package com.ntu.api.controller.additional.admin;

import com.ntu.api.controller.additional.admin.add.addBuildingController;
import com.ntu.api.controller.additional.admin.add.addCourseFacultyController;
import com.ntu.api.controller.additional.admin.add.addCurriculumDepartmentGroupController;
import com.ntu.api.controller.additional.admin.add.addLessonSpecialityController;
import com.ntu.api.controller.additional.admin.editRemove.*;
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

    @FXML public void editClassroom(){
        editRemoveClassRoomController.setBool(true);
        open("Редагування аудиторії", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveClassRoom.fxml","Помилка редагування аудиторії");
    }
    @FXML public void editCourse(){
        editRemoveCourseFacultyController.setBool(true);
        editRemoveCourseFacultyController.setFlag(1);
        open("Редагування курсу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourseFaculty.fxml","Помилка редагування курсу");
    }
    @FXML public void editCurriculum(){
        editRemoveCurriculumDepartmentGroupController.setBool(true);
        editRemoveCurriculumDepartmentGroupController.setFlag(1);
        open("Редагування освітньої програми", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка редагування освітньої програми");
    }
    @FXML public void editDepartment(){
        editRemoveCurriculumDepartmentGroupController.setBool(true);
        editRemoveCurriculumDepartmentGroupController.setFlag(2);
        open("Редагування кафедри", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка редагування кафедри");
    }
    @FXML public void editGroup(){
        editRemoveCurriculumDepartmentGroupController.setBool(true);
        editRemoveCurriculumDepartmentGroupController.setFlag(3);
        open("Редагування групи", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка редагування групи");
    }
    @FXML public void editLesson(){
        editRemoveLessonSpecialityController.setBool(true);
        editRemoveCourseFacultyController.setFlag(1);
        open("Редагування заняття", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonSpeciiality.fxml","Помилка редагування заняття");
    }
    @FXML public void editTeacher(){}
    @FXML public void editSpesiality(){
        editRemoveLessonSpecialityController.setBool(true);
        editRemoveCourseFacultyController.setFlag(2);
        open("Редагування спеціальності", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonSpeciiality.fxml","Помилка редагування спеціальності");
    }
    @FXML public void editSubject(){}
    @FXML public void editFaculty(){
        editRemoveCourseFacultyController.setBool(true);
        editRemoveCourseFacultyController.setFlag(2);
        open("Редагування факультет", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourseFaculty.fxml","Помилка редагування факультету");
    }

    @FXML public void deleteBuilding(){
        editRemoveBuildingController.setBool(false);
        open("Видалення корпусу","/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuilding.fxml","Помилка видалення корпусу");
    }
    @FXML public void deleteClassroom(){
        editRemoveClassRoomController.setBool(false);
        open("Видалення аудиторії", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveClassRoom.fxml","Помилка видалення аудиторії");
    }
    @FXML public void deleteCourse(){
        editRemoveCourseFacultyController.setBool(false);
        editRemoveCourseFacultyController.setFlag(1);
        open("Видалення курсу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourseFaculty.fxml","Помилка видалення курсу");
    }
    @FXML public void deleteCurriculum(){
        editRemoveCurriculumDepartmentGroupController.setBool(false);
        editRemoveCurriculumDepartmentGroupController.setFlag(1);
        open("Видалення освітньої програми", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка видалення освітньої програми");
    }
    @FXML public void deleteDepartment(){
        editRemoveCurriculumDepartmentGroupController.setBool(false);
        editRemoveCurriculumDepartmentGroupController.setFlag(2);
        open("Видалення кафедри", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка видалення кафедри");
    }
    @FXML public void deleteGroup(){
        editRemoveCurriculumDepartmentGroupController.setBool(false);
        editRemoveCurriculumDepartmentGroupController.setFlag(3);
        open("Видалення групи", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCurriculumDepartmentGroup.fxml", "Помилка видалення групи");
    }
    @FXML public void deleteLesson(){
        editRemoveLessonSpecialityController.setBool(false);
        editRemoveCourseFacultyController.setFlag(1);
        open("Редагування заняття", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonSpeciiality.fxml","Помилка видалення заняття");
    }
    @FXML public void deleteTeacher(){}
    @FXML public void deleteSpesiality(){
        editRemoveLessonSpecialityController.setBool(false);
        editRemoveCourseFacultyController.setFlag(2);
        open("Редагування спеціальності", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonSpeciiality.fxml","Помилка видалення спеціальності");
    }
    @FXML public void deleteSubject(){}
    @FXML public void deleteFaculty(){
        editRemoveCourseFacultyController.setBool(false);
        editRemoveCourseFacultyController.setFlag(2);
        open("Видалення факультет", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourseFaculty.fxml","Помилка видалення факультету");
    }


}
