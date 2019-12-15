package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Semester;
import com.ntu.api.domain.database.entity.Subjects;

import java.io.File;
import java.util.List;

public interface SubjectServiceInt {
    Long addSubject(Subjects subject);
    Subjects getSubject(Long id);
    void updateSubject(Subjects subject);
    void deleteSubject(Subjects subject);
    List<Subjects> getSubjectList();
    List<String> getParametersInString(Subjects subjects);
    List<String> getSubjectOnSemester(Semester semester);
    List<Subjects> getSubjectOnSemesterList(Semester semester);
    void addSubjectFromFile(File file);

}
