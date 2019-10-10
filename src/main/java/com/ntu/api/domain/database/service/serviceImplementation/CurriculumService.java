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
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            addCurriculum(new Curriculum(name,
            Lists.getSpecialityService().getSpecialities().get(Lists.getSpecialityNameList().indexOf(list.get(2))),
            Lists.getDepartmentService().getDepartments().get(Lists.getDepartmentNameList().indexOf(list.get(3))),
                    list.get(1)));
        }
    }

//    @Override
//    public List<Curriculum> getCurriculumByDepartment(Department department) {
//        List<Curriculum> curriculumList = new ArrayList<>();
//        List<Curriculum> curriculums = curriculumDAO.findAll();
//        Department tempDepartment;
//        for(Curriculum curriculum: curriculums){
//            tempDepartment = departmentDAO.get(curriculum.getDepartment().getDepartmentId());
//            if(tempDepartment.getDepartmentId() == department.getDepartmentId()){
//                curriculumList.add(curriculum);
//            }
//        }
//        return curriculumList;
//    }

    @Override
    public List<Curriculum> getCurriculumByDepartment(Department department) {
        return department.getCurriculums();
    }

//    @Override
//    public List<String> getCurriculumsByDepartmentNames(Department department) {
//        List<String> curriculumsList = new ArrayList<>();
//        List<Curriculum> curriculums = curriculumDAO.findAll();
//        Department tempDepartment;
//        for(Curriculum curriculum: curriculums){
//            tempDepartment = departmentDAO.get(curriculum.getDepartment().getDepartmentId());
//            if(tempDepartment.getDepartmentId() == department.getDepartmentId()){
//                curriculumsList.add(curriculum.getCurriculumName());
//            }
//        }
//        return curriculumsList;
//    }

    @Override
    public List<String> getCurriculumsByDepartmentNames(Department department) {
        List<String> curriculumsList = new ArrayList<>();
        for(Curriculum curriculum: department.getCurriculums()){
            curriculumsList.add(curriculum.getCurriculumName());
        }
        return curriculumsList;
    }

//    @Override
//    public List<Curriculum> getCurriculumsBySpeciality(Speciality speciality) {
//        List<Curriculum> curriculumList = new ArrayList<>();
//        List<Curriculum> curriculums = curriculumDAO.findAll();
//        Speciality tempSpeciality;
//        for(Curriculum curriculum: curriculums){
//            tempSpeciality = specialityDAO.get(curriculum.getSpeciality().getSpecialityId());
//            if(tempSpeciality.getSpecialityId() == speciality.getSpecialityId()){
//                curriculumList.add(curriculum);
//            }
//        }
//        return curriculumList;
//    }

    @Override
    public List<Curriculum> getCurriculumsBySpeciality(Speciality speciality) {
        return speciality.getCurriculums();
    }

//    @Override
//    public List<String> getCurriculumsBySpecialityNames(Speciality speciality) {
//        List<String> curriculumsList = new ArrayList<>();
//        List<Curriculum> curriculums = curriculumDAO.findAll();
//        Speciality tempSpeciality;
//        for(Curriculum curriculum: curriculums){
//            tempSpeciality = specialityDAO.get(curriculum.getSpeciality().getSpecialityId());
//            if(tempSpeciality.getSpecialityId() == speciality.getSpecialityId()){
//                curriculumsList.add(curriculum.getCurriculumName());
//            }
//        }
//        return curriculumsList;
//    }

    @Override
    public List<String> getCurriculumsBySpecialityNames(Speciality speciality) {
        List<String> curriculumsList = new ArrayList<>();
        for(Curriculum curriculum: speciality.getCurriculums()){
            curriculumsList.add(curriculum.getCurriculumName());
        }
        return curriculumsList;
    }

}
