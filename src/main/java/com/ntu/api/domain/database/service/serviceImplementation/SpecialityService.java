package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.SpecialityServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SpecialityService implements SpecialityServiceInt {
    @Autowired private SpecialityDAOInt specialityDAO;

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
        parameters.add(speciality.getSpecialityCode());
        parameters.add(speciality.getSpecialityName());
        return parameters;
    }

    @Override
    public void addSpecialityFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String code = list.get(0);
            String name = list.get(1);
            addSpeciality(new Speciality(code, name));
        }
    }
}
