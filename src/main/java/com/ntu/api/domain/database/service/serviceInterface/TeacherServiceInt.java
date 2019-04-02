package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Teacher;

import java.util.List;

public interface TeacherServiceInt {
    Long addTeacher(Teacher teacher);
    Teacher getTeacher(Long id);
    void updateTeacher (Teacher teacher);
    void deleteTeacher (Teacher teacher);
    List<Teacher> getTeachers();
}
