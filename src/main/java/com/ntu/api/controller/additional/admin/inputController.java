package com.ntu.api.controller.additional.admin;

import com.ntu.api.controller.additional.admin.add.addBuildingSpecialityController;
import com.ntu.api.controller.additional.admin.add.addCurriculumController;
import com.ntu.api.controller.additional.admin.add.addTeacherGroupController;
import com.ntu.api.controller.additional.admin.editRemove.*;
import com.ntu.api.domain.database.service.serviceInterface.*;
import com.ntu.api.model.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

public class inputController {
    @FXML private AnchorPane adminDlgInput;
    ApplicationContext context = new ClassPathXmlApplicationContext(
            "com/ntu/api/spring/database/config.xml");
    private BuildingServiceInt buildingService = context.getBean(BuildingServiceInt.class);
    private ClassRoomServiceInt classRoomService = context.getBean(ClassRoomServiceInt.class);
    private CourseServiceInt courseService = context.getBean(CourseServiceInt.class);
    private CurriculumServiceInt curriculumService = context.getBean(CurriculumServiceInt.class);
    private DepartmentServiceInt departmentService = context.getBean(DepartmentServiceInt.class);
    private GroupServiceInt groupService = context.getBean(GroupServiceInt.class);
    private LessonServiceInt lessonService = context.getBean(LessonServiceInt.class);
    private TeacherServiceInt teacherService = context.getBean(TeacherServiceInt.class);
    private SpecialityServiceInt specialityService = context.getBean(SpecialityServiceInt.class);
    private SubjectServiceInt subjectService = context.getBean(SubjectServiceInt.class);
    private FacultyServiceInt facultyService = context.getBean(FacultyServiceInt.class);

    private static FileChooser chooser = new FileChooser();
    private static File fileName;

    @FXML public void okOnClick(){
        Stage dlg = (Stage) adminDlgInput.getScene().getWindow();
        dlg.close();
    }

    @FXML public void addBuilding(){
        addBuildingSpecialityController.setCounter(1);
        open("Додавання корпусу", "/com/ntu/api/javafx/model/additional/admin/add/addBuildingSpeciality.fxml", "Помилка додавання корпусу");
    }
    @FXML public void addClassroom(){
        open("Додавання аудиторії", "/com/ntu/api/javafx/model/additional/admin/add/addClassRoom.fxml", "Помилка додавання аудиторії");
    }
    @FXML public void addCourse(){
        open("Додавання курсу", "/com/ntu/api/javafx/model/additional/admin/add/addCourse.fxml", "Помилка додавання курсу");
    }
    @FXML public void addCurriculum(){
        open("Додавання освітньої програми", "/com/ntu/api/javafx/model/additional/admin/add/addCurriculum.fxml", "Помилка додавання освітньої програми");
    }
    @FXML public void addDepartment(){
        open("Додавання кафедри", "/com/ntu/api/javafx/model/additional/admin/add/addDepartment.fxml", "Помилка додавання кафедри");
    }
    @FXML public void addGroup(){
        addTeacherGroupController.setCounter(2);
        open("Додавання групи", "/com/ntu/api/javafx/model/additional/admin/add/addTeacherGroup.fxml", "Помилка додавання групи");
    }
    @FXML public void addLesson(){
        open("Додавання заняття", "/com/ntu/api/javafx/model/additional/admin/add/addLesson.fxml", "Помилка додавання заняття");
    }
    @FXML public void addTeacher(){
        addTeacherGroupController.setCounter(1);
        open("Додати викладача", "/com/ntu/api/javafx/model/additional/admin/add/addTeacherGroup.fxml", "Помилка додавання викладача");
    }
    @FXML public void addSpesiality(){
        addBuildingSpecialityController.setCounter(2);
        open("Додавання спеціальності", "/com/ntu/api/javafx/model/additional/admin/add/addBuildingSpeciality.fxml", "Помилка додавання спеціальності");
    }
    @FXML public void addSubject(){
        open("Додавання дисципліни", "/com/ntu/api/javafx/model/additional/admin/add/addSubject.fxml", "Помилка додавання дисципліни");
    }
    @FXML public void addFaculty(){
        open("Додавання факультету", "/com/ntu/api/javafx/model/additional/admin/add/addFaculty.fxml", "Помилка додавання факультету");
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
        editRemoveBuildingSpecialityController.setBool(true);
        editRemoveBuildingSpecialityController.setFlag(1);
        open("Редагування корпусу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuildingSpeciality.fxml","Помилка редагування корпусу");
    }

    @FXML public void editClassroom(){
        editRemoveClassRoomController.setBool(true);
        open("Редагування аудиторії", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveClassRoom.fxml","Помилка редагування аудиторії");
    }
    @FXML public void editCourse(){
        editRemoveCourseController.setBool(true);
        open("Редагування курсу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourse.fxml","Помилка редагування курсу");
    }
    @FXML public void editCurriculum(){
        editRemoveLessonCurriculumController.setFlag(2);
        editRemoveLessonCurriculumController.setBool(true);
        open("Редагування освітньої програми", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonCurriculum.fxml", "Помилка редагування освітньої програми");
    }
    @FXML public void editDepartment(){
        editRemoveDepartmentController.setBool(true);
        open("Редагування кафедри", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveDepartment.fxml", "Помилка редагування кафедри");
    }
    @FXML public void editGroup(){
        editRemoveTeacherGroupController.setFlag(2);
        editRemoveTeacherGroupController.setBool(true);
        open("Редагування групи", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveTeacherGroup.fxml", "Помилка редагування групи");
    }
    @FXML public void editLesson(){
        editRemoveLessonCurriculumController.setFlag(1);
        editRemoveLessonCurriculumController.setBool(true);
        open("Редагування заняття", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonCurriculum.fxml","Помилка редагування заняття");
    }
    @FXML public void editTeacher(){
        editRemoveTeacherGroupController.setFlag(1);
        editRemoveTeacherGroupController.setBool(true);
        open("Редагування викладача", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveTeacherGroup.fxml","Помилка редагування викладача");
    }
    @FXML public void editSpesiality(){
        editRemoveBuildingSpecialityController.setBool(true);
        editRemoveBuildingSpecialityController.setFlag(2);
        open("Редагування спеціальності", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuildingSpeciality.fxml","Помилка редагування спеціальності");
    }
    @FXML public void editSubject(){
        editRemoveSubjectController.setBool(true);
        open("Редагування дисципліни", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveSubject.fxml", "Помилка редагування дисципліни");
    }
    @FXML public void editFaculty(){
        editRemoveFacultyController.setBool(true);
        open("Редагування факультет", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveFaculty.fxml","Помилка редагування факультету");
    }

    @FXML public void deleteBuilding(){
        editRemoveBuildingSpecialityController.setBool(false);
        editRemoveBuildingSpecialityController.setFlag(1);
        open("Видалення корпусу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuildingSpeciality.fxml","Помилка видалення корпусу");
    }
    @FXML public void deleteClassroom(){
        editRemoveClassRoomController.setBool(false);
        open("Видалення аудиторії", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveClassRoom.fxml","Помилка видалення аудиторії");
    }
    @FXML public void deleteCourse(){
        editRemoveCourseController.setBool(false);
        open("Видалення курсу", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveCourse.fxml","Помилка видалення курсу");
    }
    @FXML public void deleteCurriculum(){
        editRemoveLessonCurriculumController.setBool(false);
        editRemoveLessonCurriculumController.setFlag(2);
        open("Видалення освітньої програми", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonCurriculum.fxml", "Помилка видалення освітньої програми");
    }
    @FXML public void deleteDepartment(){
        editRemoveDepartmentController.setBool(false);
        open("Видалення кафедри", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveDepartment.fxml", "Помилка видалення кафедри");
    }
    @FXML public void deleteGroup(){
        editRemoveTeacherGroupController.setFlag(2);
        editRemoveTeacherGroupController.setBool(false);
        open("Видалення групи", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveTeacherGroup.fxml", "Помилка видалення групи");
    }
    @FXML public void deleteLesson(){
        editRemoveLessonCurriculumController.setBool(false);
        editRemoveLessonCurriculumController.setFlag(1);
        open("Редагування заняття", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveLessonCurriculum.fxml","Помилка видалення заняття");
    }
    @FXML public void deleteTeacher(){
        editRemoveTeacherGroupController.setFlag(1);
        editRemoveTeacherGroupController.setBool(false);
        open("Видалення викладача", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveTeacherGroup.fxml", "Помилка видалення викладача");
    }
    @FXML public void deleteSpesiality(){
        editRemoveBuildingSpecialityController.setBool(false);
        editRemoveBuildingSpecialityController.setFlag(2);
        open("Видалення спеціальності", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveBuildingSpeciality.fxml","Помилка видалення спеціальності");
    }
    @FXML public void deleteSubject(){
        editRemoveSubjectController.setBool(false);
        open("Видалення дисципліни", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveSubject.fxml", "Помилка видалення дисципліни");
    }
    @FXML public void deleteFaculty(){
        editRemoveFacultyController.setBool(false);
        open("Видалення факультет", "/com/ntu/api/javafx/model/additional/admin/editRemove/editRemoveFaculty.fxml","Помилка видалення факультету");
    }

    @FXML public void buildingFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        buildingService.addBuildingFromFile(fileName);
    }
    @FXML public void classRoomFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        classRoomService.addClassRoomFromFile(fileName);
    }
    @FXML public void courseFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        courseService.addCourseFromFile(fileName);
    }
    @FXML public void curriculumFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        curriculumService.addCurriculumsFromFile(fileName);
    }
    @FXML public void departmentFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        departmentService.addDepartmentFromFile(fileName);
    }
    @FXML public void groupFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        groupService.addGroupFromFile(fileName);
    }
    @FXML public void lessonFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        lessonService.addLessonFromFile(fileName);
    }
    @FXML public void teacherFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        teacherService.addTeacherFromFile(fileName);
    }
    @FXML public void spesialityFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        specialityService.addSpecialityFromFile(fileName);
    }
    @FXML public void subjectFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        subjectService.addSubjectFromFile(fileName);
    }
    @FXML public void facultyFromFile(){
        fileName = chooser.showOpenDialog(adminDlgInput.getScene().getWindow());
        facultyService.addFacultyFromFile(fileName);
    }




}
