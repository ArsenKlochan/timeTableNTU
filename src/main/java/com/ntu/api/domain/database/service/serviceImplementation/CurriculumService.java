package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CurriculumService implements CurriculumServiceInt {
    @Autowired private CurriculumDAOInt curriculumDAO;
    @Autowired private DepartmentDAOInt departmentDAO;
    @Autowired private SpecialityDAOInt specialityDAO;

    @Override
    public Long addCurriculum(Curriculum curriculum) {
        return curriculumDAO.create(curriculum);
    }
    @Override
    public Curriculum getCurriculum(Long id) {
        return curriculumDAO.get(id);
    }
    @Override
    public void updateCurriculum(Curriculum curriculum) {
        curriculumDAO.update(curriculum);
    }
    @Override
    public void deleteCurriculum(Curriculum curriculum) {
        curriculumDAO.delete(curriculum);
    }
    @Override
    public List<Curriculum> getCurriculums() {
        return curriculumDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(Curriculum curriculum) {
        List<String> parameters = new ArrayList<>();
        Department department = departmentDAO.get(curriculum.getDepartment().getDepartmentId());
        Speciality speciality = specialityDAO.get(curriculum.getSpeciality().getSpecialityId());
        parameters.add(curriculum.getCurriculumName());
        parameters.add(department.getDepartmentName());
        parameters.add(speciality.getSpecialityName());
        return parameters;
    }
}
