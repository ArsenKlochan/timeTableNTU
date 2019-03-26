package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService implements DepartmentServiceInt {
    @Autowired
    private DepartmentDAOInt departmentDAO;

    @Override
    public Long addDepartment(Department department) {
        return departmentDAO.create(department);
    }
    @Override
    public Department getDepartment(Long id) {
        return departmentDAO.get(id);
    }
    @Override
    public void updateDepartment(Department department) {
        departmentDAO.update(department);
    }
    @Override
    public void deleteDepartment(Long id) {
        departmentDAO.delete(id);
    }
    @Override
    public List<Department> getDepartments() {
        return departmentDAO.findAll();
    }
}
