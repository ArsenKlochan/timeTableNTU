package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.DepartmentServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
        Faculty faculty = facultyDAO.get(departmentDAO.get(department.getDepartmentId()).getFaculty().getFacultyId());
        parameters.add(department.getDepartmentCode().replace(" ", ""));
        parameters.add(department.getDepartmentName());
        parameters.add(faculty.getFacultyName());
        return parameters;
    }

    @Override
    public List<String> getDepartmentsOnFaculty(Faculty faculty) {
        List<String> departmentsList = new ArrayList<>();
        for(Department department: facultyDAO.get(faculty.getFacultyId()).getDepartments()){
            departmentsList.add(department.getDepartmentCode() + " " + department.getDepartmentName());
        }
        return departmentsList;
    }

    @Override
    public List<Department> getDepartmentsOnFacultyList(Faculty faculty) {
        ArrayList<Department> departments = new ArrayList<>();
        for(Department department:facultyDAO.get(faculty.getFacultyId()).getDepartments()){
            departments.add(department);
        }
        return departments;
    }

    @Override
    public void addDepartmentFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String code = list.get(0);
            String name = list.get(1);
            addDepartment(new Department(code, name,
                    Lists.getFacultyService().getFaculties().get(Lists.getFacultyList().indexOf(list.get(2)))));
        }
    }
}
