package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.LessonDAOInt;
import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.service.serviceInterface.LessonServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class LessonService implements LessonServiceInt {
    @Autowired
    private LessonDAOInt lessonDAO;

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
    public void deleteLesson(Long id) {
        lessonDAO.delete(id);
    }

    @Override
    public List<Lesson> getLessons() {
        return lessonDAO.findAll();
    }
}
