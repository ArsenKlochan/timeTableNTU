package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.entity.Faculty;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class FacultyDAO implements FacultyDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Faculty faculty) {
        return (Long)factory.getCurrentSession().save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return factory.getCurrentSession().get(Faculty.class, id);
    }

    @Override
    public void update(Faculty faculty) {
        factory.getCurrentSession().saveOrUpdate(faculty);
    }

    @Override
    public void delete(Faculty faculty) {
        factory.getCurrentSession().delete(faculty);
    }

    @Override
    public List<Faculty> findAll() {
        return factory.getCurrentSession().createQuery("from Faculty").list();
    }
}
