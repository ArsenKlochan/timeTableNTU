package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
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
    @Autowired private CourseDAOInt courseDAO;

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
        Course course = courseDAO.get(subjects.getCourse().getCourseId());
        parameters.add(subjects.getSubjectName());
        parameters.add(course.getCourseName());
        parameters.add(Integer.toString(subjects.getLection()));
        parameters.add(Integer.toString(subjects.getPractic()));
        parameters.add(Integer.toString(subjects.getLabaratory()));
        parameters.add(Integer.toString(subjects.getAllHours()));
        parameters.add(subjects.getExamType().toString());
        return parameters;
    }

    @Override
    public List<String> getSubjectOnCourse(Course course) {
        List<String> subjectList = new ArrayList<>();
        for(Subjects subject: courseDAO.get(course.getCourseId()).getSubjects()){
            subjectList.add(subject.getSubjectName());
        }
        return subjectList;
    }

    @Override
    public List<Subjects> getSubjectOnCourseList(Course course) {
        return courseDAO.get(course.getCourseId()).getSubjects();
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
                    Lists.getCourseService().getCourses().get(Lists.getCourseList().indexOf(list.get(6)))));
        }
    }
}
