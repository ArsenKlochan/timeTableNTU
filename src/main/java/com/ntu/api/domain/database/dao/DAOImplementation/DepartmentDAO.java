package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.entity.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DepartmentDAO implements DepartmentDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(Department department) {
        return (Long)factory.getCurrentSession().save(department);
    }

    @Override
    public Department get(Long id) {
        return factory.getCurrentSession().get(Department.class, id);
    }

    @Override
    public void update(Department department) {
        factory.getCurrentSession().saveOrUpdate(department);
    }

    @Override
    public void delete(Department department) {
        factory.getCurrentSession().delete(department);
    }

    @Override
    public List<Department> findAll() {
        return factory.getCurrentSession().createQuery("from Department").list();
    }
}
