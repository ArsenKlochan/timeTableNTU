package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOinterface.LessonDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.SubjectDAOInt;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.LessonType;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class LessonService implements LessonServiceInt {
    @Autowired private LessonDAOInt lessonDAO;
    @Autowired private SubjectDAOInt subjectDAO;

    @Override
    public Long addLesson(Lesson lesson) {
        return lessonDAO.create(lesson);
    }

    @Override
    public Lesson getLesson(Long id) {
        return lessonDAO.get(id);
    }

    @Override
    public void updateLesson(Lesson lesson) {
        lessonDAO.update(lesson);
    }

    @Override
    public void deleteLesson(Lesson lesson) {
        lessonDAO.delete(lesson);
    }

    @Override
    public List<Lesson> getLessons() {
        return lessonDAO.findAll();
    }

    @Override
    public List<String> getParametersInString(Lesson lesson) {
        List<String> parameters = new ArrayList<>();
        Subjects subjects = subjectDAO.get(lesson.getSubject().getSubjectId());
        parameters.add(lesson.getLessonName());
        parameters.add(lesson.getLessonType().toString());
        parameters.add(subjects.getSubjectName());
        return parameters;
    }

    @Override
    public List<String> getLessonsOnSubject(Subjects subjects) {
        List<String> lessonsList = new ArrayList<>();
        for(Lesson lesson: subjects.getLessons()){
            lessonsList.add(lesson.getLessonType() + " " + lesson.getLessonName());
        }
        return lessonsList;
    }

    @Override
    public List<Lesson> getLessonsOnSubjectList(Subjects subjects) {
        return subjects.getLessons();
    }

    @Override
    public void addLessonFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            addLesson(new Lesson(name, LessonType.valueOf(list.get(1)),
                    Lists.getSubjectService().getSubjectList().get(Lists.getSubjectsList().indexOf(list.get(2)))));
        }
    }
}
