package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.*;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.entity.enums.Qualification;
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
    @Autowired private CourseDAOInt courseDAO;
    @Autowired private SemesterDAOInt semesterDAO;

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
            for (ArrayList<String> list : ExcelReader.excelRead(file.getAbsolutePath())) {
                    String name = list.get(0);
                    String shortName = list.get(4);
                    addCurriculumCourseSemester(new Curriculum(name, shortName,
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
    @Override
    public void addCurriculumCourseSemester(Curriculum curriculum){
        addCurriculum(curriculum);
        Course course;
        if(curriculum.getQualification().equals("Бакалавр")){
            for(int i = 1; i <= 4; i++){
                course = new Course(curriculum.getShortName() + " - " + i, curriculum);
                courseDAO.create(course);
                semesterCreater(course);
            }
        }
        else if (curriculum.getQualification().equals("Магістр")){
            course = new Course(curriculum.getShortName() + " - 1м", curriculum);
            courseDAO.create(course);
            semesterCreater(course);
        }
    }

    private void semesterCreater(Course course){
        semesterDAO.create(new Semester("I", course));
        semesterDAO.create(new Semester("II", course));
    }


}
