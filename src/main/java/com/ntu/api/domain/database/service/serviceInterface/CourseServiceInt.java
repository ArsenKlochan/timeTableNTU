package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.io.File;
import java.util.List;

public interface CourseServiceInt {
    Long addCourse(Course course);
    Course getCourse(Long id);
    void updateCourse (Course course);
    void deleteCourse(Course course);
    List<Course> getCourses();
    List<String> getParametersInString(Course course);
    List<String> getCourseOnCurriculuminString(Curriculum curriculum);
    List<Course> getCourseOnCurriculumList(Curriculum curriculum);
    void addCourseFromFile(File file);
}
