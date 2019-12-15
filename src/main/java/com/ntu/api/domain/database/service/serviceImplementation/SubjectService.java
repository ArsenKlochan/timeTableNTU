package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.SemesterDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Semester;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.ExamType;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class SubjectService implements SubjectServiceInt {
    @Autowired private SubjectDAOInt subjectDAO;
    @Autowired private SemesterDAOInt semesterDAO;

    @Override
    public Long addSubject(Subjects subject) {
        return subjectDAO.create(subject);
    }

    @Override
    public Subjects getSubject(Long id) {
        return subjectDAO.get(id);
    }

    @Override
    public void updateSubject(Subjects subject) {
        subjectDAO.update(subject);
    }

    @Override
    public void deleteSubject(Subjects subject) {
        subjectDAO.delete(subject);
    }

    @Override
    public List<Subjects> getSubjectList() {
        return subjectDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Subjects subjects) {
        List<String> parameters = new ArrayList<>();
        Semester semester = semesterDAO.get(subjects.getSemester().getSemesterId());
        parameters.add(subjects.getSubjectName());
        parameters.add(semester.getSemesterName());
        parameters.add(Integer.toString(subjects.getLection()));
        parameters.add(Integer.toString(subjects.getPractic()));
        parameters.add(Integer.toString(subjects.getLabaratory()));
        parameters.add(Integer.toString(subjects.getAllHours()));
        parameters.add(subjects.getExamType().toString());
        return parameters;
    }

    @Override
    public List<String> getSubjectOnSemester(Semester semester) {
        List<String> subjectList = new ArrayList<>();
        for(Subjects subject: semesterDAO.get(semester.getSemesterId()).getSubjects()){
            subjectList.add(subject.getSubjectName());
        }
        return subjectList;
    }

    @Override
    public List<Subjects> getSubjectOnSemesterList(Semester semester) {
        ArrayList<Subjects> subjects = new ArrayList<>();
        for(Subjects subject: semesterDAO.get(semester.getSemesterId()).getSubjects()){
            subjects.add(subject);
        }
        return subjects;
    }

    @Override
    public void addSubjectFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            int lection = Integer.parseInt(list.get(1));
            int practic = Integer.parseInt(list.get(2));
            int labaratory = Integer.parseInt(list.get(3));
            int allHours = Integer.parseInt(list.get(4));
            addSubject(new Subjects(name, lection, practic, labaratory, allHours, ExamType.valueOf(list.get(5)),
                    Lists.getSemesterService().getSemesters().get(Lists.getSemesterList().indexOf(list.get(6)))));
        }
    }
}
