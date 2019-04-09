package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.CourseDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Course;
import com.ntu.api.domain.database.entity.Curriculum;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.service.serviceInterface.SubjectServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ArrayList<Curriculum> curriculumList = (ArrayList<Curriculum>) subjects.getCurriculums();
        Course course = courseDAO.get(subjects.getCourse().getCourseId());
        parameters.add(subjects.getSubjectName());
        parameters.add(" ");
        parameters.add(course.getCourseName());
        parameters.add(Integer.toString(subjects.getLection()));
        parameters.add(Integer.toString(subjects.getPractic()));
        parameters.add(Integer.toString(subjects.getLabaratory()));
        parameters.add(Integer.toString(subjects.getAllHours()));
        parameters.add(subjects.getExamType().toString());
        return parameters;
    }
}
