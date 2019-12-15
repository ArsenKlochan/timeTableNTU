package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.CurriculumDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.FacultyDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SpecialityDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.CurriculumServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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

    @Override
    public void addCurriculumsFromFile(File file) {
        System.out.println(Lists.getSpecialityNameList());
            for (ArrayList<String> list : ExcelReader.excelRead(file.getAbsolutePath())) {
                    String name = list.get(0);
                    String shortName = list.get(4);
                    addCurriculum(new Curriculum(name, shortName,
                            Lists.getSpecialityService().getSpecialities().get(Lists.getSpecialityNameList().indexOf(list.get(2))),
                            Lists.getDepartmentService().getDepartments().get(Lists.getDepartmentNameList().indexOf(list.get(3))),
                            list.get(1)));
            }

    }

    @Override
    public List<Curriculum> getCurriculumByDepartment(Department department) {
        ArrayList<Curriculum> curriculums = new ArrayList<>();
        for(Curriculum curriculum: departmentDAO.get(department.getDepartmentId()).getCurriculums()){
            curriculums.add(curriculum);
        }
        return curriculums;
    }

    @Override
    public List<String> getCurriculumsByDepartmentNames(Department department) {
        List<String> curriculumsList = new ArrayList<>();
        for(Curriculum curriculum: departmentDAO.get(department.getDepartmentId()).getCurriculums()){
            curriculumsList.add(curriculum.getCurriculumName() + " " + curriculum.getQualification());
        }
        return curriculumsList;
    }

    @Override
    public List<Curriculum> getCurriculumsBySpeciality(Speciality speciality) {
        ArrayList<Curriculum> curriculums = new ArrayList<>();
        for(Curriculum curriculum: specialityDAO.get(speciality.getSpecialityId()).getCurriculums()){
            curriculums.add(curriculum);
        }
        return curriculums;
    }

    @Override
    public List<String> getCurriculumsBySpecialityNames(Speciality speciality) {
        List<String> curriculumsList = new ArrayList<>();
        for(Curriculum curriculum: specialityDAO.get(speciality.getSpecialityId()).getCurriculums()){
            curriculumsList.add(curriculum.getCurriculumName() + " " + curriculum.getQualification());
        }
        return curriculumsList;
    }

}
