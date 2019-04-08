package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SpecialityService implements SpecialityServiceInt {
    @Autowired private SpecialityDAOInt specialityDAO;
    @Autowired private DepartmentDAOInt departmentDAO;
    @Autowired private CurriculumDAOInt curriculumDAO;

    @Override
    public Long addSpeciality(Speciality speciality) {
        return specialityDAO.create(speciality);
    }

    @Override
    public Speciality getSpeciality(Long id) {
        return specialityDAO.get(id);
    }

    @Override
    public void updateSpeciality(Speciality speciality) {
        specialityDAO.update(speciality);
    }

    @Override
    public void deleteSpeciality(Speciality speciality) {
        specialityDAO.delete(speciality);
    }

    @Override
    public List<Speciality> getSpecialities() {
        return specialityDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Speciality speciality) {
        List<String> parameters = new ArrayList<>();
        Department department = departmentDAO.get(speciality.getDepartment().getDepartmentId());
        Curriculum curriculum = curriculumDAO.get(speciality.getCurriculum().getCurriculumId());
        parameters.add(speciality.getSpecialityName());
        parameters.add(department.getDepartmentName());
        parameters.add(curriculum.getCurriculumName());
        return parameters;
    }
}
