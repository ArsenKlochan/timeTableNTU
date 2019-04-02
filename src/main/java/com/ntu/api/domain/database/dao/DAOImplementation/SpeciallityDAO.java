package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.Speciality;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SpeciallityDAO implements SpecialityDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Speciality speciality) {
        return (Long)factory.getCurrentSession().save(speciality);
    }

    @Override
    public Speciality get(Long id) {
        return factory.getCurrentSession().get(Speciality.class, id);
    }

    @Override
    public void update(Speciality speciality) {
        factory.getCurrentSession().saveOrUpdate(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        factory.getCurrentSession().delete(speciality);
    }

    @Override
    public List<Speciality> findAll() {
        return factory.getCurrentSession().createQuery("from Speciality").list();
    }
}
