package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DepartmentService implements DepartmentServiceInt {
    @Autowired private DepartmentDAOInt departmentDAO;
    @Autowired private FacultyDAOInt facultyDAO;

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
    public void deleteDepartment(Department department) {
        departmentDAO.delete(department);
    }
    @Override
    public List<Department> getDepartments() {
        return departmentDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Department department) {
        List<String> parameters = new ArrayList<>();
        Faculty faculty = facultyDAO.get(department.getFaculty().getFacultyId());
        parameters.add(department.getDepartmentCode());
        parameters.add(department.getDepartmentName());
        parameters.add(faculty.getFacultyName());
        return parameters;
    }

    @Override
    public List<String> getDepartmentsOnFaculty(Faculty faculty) {
        List<String> departmentsList = new ArrayList<>();
        List<Department> departments = departmentDAO.findAll();
        Faculty tempFaculty;
        for(Department department: departments){
            tempFaculty = facultyDAO.get(department.getFaculty().getFacultyId());
            if(tempFaculty.getFacultyId()==faculty.getFacultyId()){
                departmentsList.add(department.getDepartmentCode() + " " + department.getDepartmentName());
            }
        }
        return departmentsList;
    }

    @Override
    public List<Department> getDepartmentsOnFacultyList(Faculty faculty) {
        List<Department> departmentsList = new ArrayList<>();
        List<Department> departments = departmentDAO.findAll();
        Faculty tempFaculty;
        for(Department department: departments){
            tempFaculty = facultyDAO.get(department.getFaculty().getFacultyId());
            if(tempFaculty.getFacultyId()==faculty.getFacultyId()){
                departmentsList.add(department);
            }
        }
        return departmentsList;
    }


}
