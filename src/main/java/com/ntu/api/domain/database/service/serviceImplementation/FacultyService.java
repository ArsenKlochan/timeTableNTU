package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.BuildingDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FacultyService implements FacultyServiceInt {
    @Autowired private FacultyDAOInt facultyDAO;
    @Autowired private BuildingDAOInt buildingDAO;

    @Override
    public Long addFaculty(Faculty faculty) {
        return facultyDAO.create(faculty);
    }
    @Override
    public Faculty getFaculty(Long id) {
        return facultyDAO.get(id);
    }
    @Override
    public void updateFaculty(Faculty faculty) {
        facultyDAO.update(faculty);
    }
    @Override
    public void deleteFacultu(Faculty faculty) {
        facultyDAO.delete(faculty);
    }
    @Override
    public List<Faculty> getFaculties() {
        return facultyDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(Faculty faculty){
        List<String> parameters = new ArrayList<>();
        parameters.add(faculty.getFacultyName());
        return parameters;
    }

    @Override
    public void addFacultyFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            addFaculty(new Faculty(name));
        }
    }
}