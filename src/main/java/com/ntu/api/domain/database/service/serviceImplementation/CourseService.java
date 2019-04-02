package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Group;
import com.ntu.api.domain.database.service.serviceInterface.CourseServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService implements CourseServiceInt {
    @Autowired
    private CourseDAOInt courseDAO;

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
}
