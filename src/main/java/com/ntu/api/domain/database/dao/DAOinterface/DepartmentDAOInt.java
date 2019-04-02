package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Department;

import java.util.List;

public interface DepartmentDAOInt {
    Long create(Department department);

    Department get (Long id);

    void update(Department department);

    void delete(Department department);

    List<Department> findAll();
}
