package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.entity.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CourseDAO implements CourseDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Course course) {
        return (Long)factory.getCurrentSession().save(course);
    }

    @Override
    public Course get(Long id) {
        return factory.getCurrentSession().get(Course.class, id);
    }

    @Override
    public void update(Course course) {
        factory.getCurrentSession().saveOrUpdate(course);
    }

    @Override
    public void delete(Course course) {
        factory.getCurrentSession().delete(course);
    }

    @Override
    public List<Course> findAll() {
        return factory.getCurrentSession().createQuery("from Course").list();
    }
}
