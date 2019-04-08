package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Subjects;

import java.util.List;

public interface SubjectServiceInt {
    Long addSubject(Subjects subject);
    Subjects getSubject(Long id);
    void updateSubject(Subjects subject);
    void deleteSubject(Subjects subject);
    List<Subjects> getSubjectList();
    List<String> getParametersInString(Subjects subjects);

}
