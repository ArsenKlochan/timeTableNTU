package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.util.List;

public interface SpecialityServiceInt {
    Long addSpeciality(Speciality speciality);
    Speciality getSpeciality(Long id);
    void updateSpeciality (Speciality speciality);
    void deleteSpeciality(Speciality speciality);
    List<Speciality> getSpecialities();
}
