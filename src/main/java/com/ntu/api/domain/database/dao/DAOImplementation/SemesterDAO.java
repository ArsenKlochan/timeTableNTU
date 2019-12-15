package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.SemesterDAOInt;
import com.ntu.api.domain.database.entity.Semester;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SemesterDAO implements SemesterDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Semester semester) {
        return (Long)factory.getCurrentSession().save(semester);
    }

    @Override
    public Semester get(Long id) {
        return factory.getCurrentSession().get(Semester.class, id);
    }

    @Override
    public void update(Semester semester) {
        factory.getCurrentSession().saveOrUpdate(semester);
    }

    @Override
    public void delete(Semester semester) {
        factory.getCurrentSession().delete(semester);
    }

    @Override
    public List<Semester> findAll() {
        return factory.getCurrentSession().createQuery("from Semester").list();
    }
}
