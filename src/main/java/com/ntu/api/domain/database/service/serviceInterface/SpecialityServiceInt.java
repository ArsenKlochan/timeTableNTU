package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Faculty;
import com.ntu.api.domain.database.entity.Speciality;

import java.util.List;

public interface SpecialityServiceInt {
    Long addSpeciality(Speciality speciality);
    Speciality getSpeciality(Long id);
    void updateSpeciality (Speciality speciality);
    void deleteSpeciality(Speciality speciality);
    List<Speciality> getSpecialities();
    List<String> getParametersInString(Speciality speciality);
}
