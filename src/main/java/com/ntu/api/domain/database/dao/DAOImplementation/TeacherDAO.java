package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.TeacherDAOInt;
import com.ntu.api.domain.database.entity.Teacher;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TeacherDAO implements TeacherDAOInt {
    @Autowired
    private SessionFactory factory;
    @Override
    public Long create(Teacher teacher) {
        return (Long)factory.getCurrentSession().save(teacher);
    }

    @Override
    public Teacher get(Long id) {
        return factory.getCurrentSession().get(Teacher.class, id);
    }

    @Override
    public void update(Teacher teacher) {
        factory.getCurrentSession().saveOrUpdate(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        factory.getCurrentSession().delete(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return factory.getCurrentSession().createQuery("from Teacher").list();
    }
}
