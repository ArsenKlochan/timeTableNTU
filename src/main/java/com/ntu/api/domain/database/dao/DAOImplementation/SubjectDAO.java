package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Subjects;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubjectDAO implements SubjectDAOInt {
    @Autowired
    private SessionFactory factory;
    @Override
    public Long create(Subjects subject) {
        return (Long)factory.getCurrentSession().save(subject);
    }

    @Override
    public Subjects get(Long id) {
        return factory.getCurrentSession().get(Subjects.class, id);
    }

    @Override
    public void update(Subjects subject) {
        factory.getCurrentSession().saveOrUpdate(subject);
    }

    @Override
    public void delete(Long id) {
        factory.getCurrentSession().delete(id);
    }

    @Override
    public List<Subjects> findAll() {
        return factory.getCurrentSession().createQuery("from Subjects").list();
    }
}
