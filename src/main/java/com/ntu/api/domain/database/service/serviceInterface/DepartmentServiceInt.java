package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.io.File;
import java.util.List;

public interface DepartmentServiceInt {
    Long addDepartment(Department department);
    Department getDepartment(Long id);
    void updateDepartment(Department department);
    void deleteDepartment(Department department);
    List<Department> getDepartments();
    List<String> getParametersInString(Department department);
    List<String> getDepartmentsOnFaculty(Faculty faculty);
    List<Department> getDepartmentsOnFacultyList(Faculty faculty);
    void addDepartmentFromFile(File file);
}
