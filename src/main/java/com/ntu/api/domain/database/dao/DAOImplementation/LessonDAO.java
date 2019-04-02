package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.LessonDAOInt;
import com.ntu.api.domain.database.entity.Lesson;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LessonDAO implements LessonDAOInt {
    @Autowired
    private SessionFactory factory;
    @Override
    public Long create(Lesson lesson) {
        return (Long)factory.getCurrentSession().save(lesson);
    }

    @Override
    public Lesson get(Long id) {
        return factory.getCurrentSession().get(Lesson.class, id);
    }

    @Override
    public void update(Lesson lesson) {
        factory.getCurrentSession().saveOrUpdate(lesson);
    }

    @Override
    public void delete(Lesson lesson) {
        factory.getCurrentSession().delete(lesson);
    }

    @Override
    public List<Lesson> findAll() {
        return factory.getCurrentSession().createQuery("from Lesson").list();
    }
}
