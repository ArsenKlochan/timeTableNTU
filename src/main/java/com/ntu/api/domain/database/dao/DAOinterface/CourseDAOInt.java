package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Course;

import java.util.List;

public interface CourseDAOInt {
    Long create(Course course);

    Course get (Long id);

    void update(Course course);

    void delete(Course course);

    List<Course> findAll();
}
