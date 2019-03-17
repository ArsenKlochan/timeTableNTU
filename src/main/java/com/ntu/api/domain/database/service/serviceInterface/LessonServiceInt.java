package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Lesson;

import java.util.List;

public interface LessonServiceInt {
    Long addLesson(Lesson lesson);
    Lesson getLesson(Long id);
    void updateLesson (Lesson lesson);
    void deleteLesson(Long id);
    List<Lesson> getLessons();
}
