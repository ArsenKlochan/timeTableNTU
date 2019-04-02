package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.UserDAOInt;
import com.ntu.api.domain.database.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDAO implements UserDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(User user) {
        return (Long)factory.getCurrentSession().save(user);
    }

    @Override
    public User get(Long id) {
        return factory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User user) {
        factory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(User user) {
        factory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> findAll() {
        return factory.getCurrentSession().createQuery("from User").list();
    }
}