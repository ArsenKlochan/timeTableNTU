package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.BuildingDAOInt;
import com.ntu.api.domain.database.entity.Building;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BuildingDAO implements BuildingDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Building building) {
        return (Long)factory.getCurrentSession().save(building);
    }

    @Override
    public Building get(Long id) {
        return factory.getCurrentSession().get(Building.class, id);
    }

    @Override
    public void update(Building building) {
        factory.getCurrentSession().saveOrUpdate(building);
    }

    @Override
    public void delete(Building building) {
        factory.getCurrentSession().delete(building);
    }

    @Override
    public List<Building> findAll() {
        return factory.getCurrentSession().createQuery("from Building").list();
    }
}
