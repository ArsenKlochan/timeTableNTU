package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.io.File;
import java.util.List;

public interface CurriculumServiceInt {
    Long addCurriculum(Curriculum curriculum);
    Curriculum getCurriculum(Long id);
    void updateCurriculum (Curriculum curriculum);
    void deleteCurriculum(Curriculum curriculum);
    List<Curriculum> getCurriculums();
    List<String> getParametersInString(Curriculum curriculum);
    void addCurriculumsFromFile(File file);
    List<Curriculum> getCurriculumByDepartment(Department department);
    List<String> getCurriculumsByDepartmentNames(Department department);
    List<Curriculum> getCurriculumsBySpeciality(Speciality speciality);
    List<String> getCurriculumsBySpecialityNames(Speciality speciality);
    public void addCurriculumCourseSemester(Curriculum curriculum);
}
