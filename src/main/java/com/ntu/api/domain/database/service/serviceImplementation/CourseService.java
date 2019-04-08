package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.entity.Speciality;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService implements CourseServiceInt {
    @Autowired private CourseDAOInt courseDAO;
    @Autowired private SpecialityDAOInt specialityDAO;

    @Override
    public Long addCourse(Course course) {
        return courseDAO.create(course);
    }
    @Override
    public Course getCourse(Long id) {
        return courseDAO.get(id);
    }
    @Override
    public void updateCourse(Course course) {
        courseDAO.update(course);
    }
    @Override
    public void deleteCourse(Course course) {
        courseDAO.delete(course);
    }
    @Override
    public List<Course> getCourses() {
        return courseDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(Course course){
        List<String> parameters = new ArrayList<>();
        Speciality speciality = specialityDAO.get(course.getSpeciality().getSpecialityId());
        parameters.add(course.getCourseName());
        parameters.add(speciality.getSpecialityName());
        return parameters;
    }
}
