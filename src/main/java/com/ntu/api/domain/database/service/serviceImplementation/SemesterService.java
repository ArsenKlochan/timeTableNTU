package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SemesterDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Semester;
import com.ntu.api.domain.database.service.serviceInterface.SemesterServiceInt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SemesterService implements SemesterServiceInt {
    @Autowired private CourseDAOInt courseDAO;
    @Autowired private SemesterDAOInt semesterDAO;

    @Override
    public Long addSemester(Semester semester) {
        return semesterDAO.create(semester);
    }

    @Override
    public Semester getSemester(Long id) {
        return semesterDAO.get(id);
    }

    @Override
    public void updateSemester(Semester semester) {
        semesterDAO.update(semester);
    }

    @Override
    public void deleteSemester(Semester semester) {
        semesterDAO.delete(semester);
    }

    @Override
    public List<Semester> getSemesters() {
        return semesterDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Semester semester) {
        List<String> parameters = new ArrayList<>();
        Course course = courseDAO.get(semester.getCourse().getCourseId());
        parameters.add(semester.getSemesterName());
        parameters.add(course.getCourseName());
        return parameters;
    }

    @Override
    public List<String> getSemestersOnCourseInString(Course course) {
        List<String> semesterOnCourse = new ArrayList<>();
        for (Semester semester: courseDAO.get(course.getCourseId()).getSemesters()){
            semesterOnCourse.add(semester.getSemesterName());
        }
        return semesterOnCourse;
    }

    @Override
    public List<Semester> getSemesterOnCourseList(Course course) {
        ArrayList<Semester> semesters = new ArrayList<>();
        for (Semester semester: courseDAO.get(course.getCourseId()).getSemesters()){
            semesters.add(semester);
        }
        return semesters;
    }
}
