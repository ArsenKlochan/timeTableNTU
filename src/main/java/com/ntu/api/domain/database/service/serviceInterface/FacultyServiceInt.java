package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.*;

import java.util.List;

public interface FacultyServiceInt {
    Long addFaculty(Faculty faculty);
    Faculty getFaculty(Long id);
    void updateFaculty(Faculty faculty);
    void deleteFacultu(Faculty faculty);
    List<Faculty> getFaculties();
    List<String> getParametersInString(Faculty faculty);
}
