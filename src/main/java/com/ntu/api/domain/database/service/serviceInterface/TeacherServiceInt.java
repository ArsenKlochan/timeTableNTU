package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Teacher;
import com.ntu.api.domain.database.entity.enums.Position;

import java.io.File;
import java.util.List;

public interface TeacherServiceInt {
    Long addTeacher(Teacher teacher);
    Teacher getTeacher(Long id);
    void updateTeacher (Teacher teacher);
    void deleteTeacher (Teacher teacher);
    List<Teacher> getTeachers();
    List<String> getParametersInString(Teacher teacher);
    List<String> getTeachersOnDepartments (Department department);
    List<Teacher> getTeacherOnDepartmentList(Department department);
    List<String> getTeachersByPosition(Position position);
    List<Teacher> getTeacherByPositionList(Position position);
    void addTeacherFromFile(File file);
}
