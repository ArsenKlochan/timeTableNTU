package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.GroupDAOInt;
import com.ntu.api.domain.database.entity.Group;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class GroupDAO implements GroupDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Group group) {
        return (Long)factory.getCurrentSession().save(group);
    }

    @Override
    public Group get(Long id) {
        return factory.getCurrentSession().get(Group.class, id);
    }

    @Override
    public void update(Group group) {
        factory.getCurrentSession().saveOrUpdate(group);
    }

    @Override
    public void delete(Group group) {
        factory.getCurrentSession().delete(group);
    }

    @Override
    public List<Group> findAll() {
        return factory.getCurrentSession().createQuery("from Group").list();
    }
}
