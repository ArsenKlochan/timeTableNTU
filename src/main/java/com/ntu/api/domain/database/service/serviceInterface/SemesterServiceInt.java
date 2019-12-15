package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Semester;

import java.util.List;

public interface SemesterServiceInt {
    Long addSemester(Semester semester);
    Semester getSemester(Long id);
    void updateSemester(Semester semester);
    void deleteSemester(Semester semester);
    List<Semester> getSemesters();
    List<String> getParametersInString(Semester semester);
    List<String> getSemestersOnCourseInString(Course course);
    List<Semester> getSemesterOnCourseList(Course course);

}
