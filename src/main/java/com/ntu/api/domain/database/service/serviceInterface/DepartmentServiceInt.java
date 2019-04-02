package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.util.List;

public interface DepartmentServiceInt {
    Long addDepartment(Department department);
    Department getDepartment(Long id);
    void updateDepartment(Department department);
    void deleteDepartment(Department department);
    List<Department> getDepartments();
}
