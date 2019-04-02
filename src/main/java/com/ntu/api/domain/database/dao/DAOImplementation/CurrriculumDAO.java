package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.entity.Curriculum;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CurrriculumDAO implements CurriculumDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Curriculum curriculum) {
        return (Long)factory.getCurrentSession().save(curriculum);
    }

    @Override
    public Curriculum get(Long id) {
        return factory.getCurrentSession().get(Curriculum.class, id);
    }

    @Override
    public void update(Curriculum curriculum) {
        factory.getCurrentSession().saveOrUpdate(curriculum);
    }

    @Override
    public void delete(Curriculum curriculum) {
        factory.getCurrentSession().delete(curriculum);
    }

    @Override
    public List<Curriculum> findAll() {
        return factory.getCurrentSession().createQuery("from Curriculum").list();
    }
}
