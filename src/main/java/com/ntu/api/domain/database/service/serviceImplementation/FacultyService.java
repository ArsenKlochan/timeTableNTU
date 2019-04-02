package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.FacultyServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacultyService implements FacultyServiceInt {
    @Autowired
    private FacultyDAOInt facultyDAO;

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
}