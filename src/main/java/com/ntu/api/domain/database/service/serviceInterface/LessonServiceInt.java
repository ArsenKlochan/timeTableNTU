package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Lesson;
import com.ntu.api.domain.database.entity.Subjects;
import com.ntu.api.domain.database.entity.enums.LessonType;

import java.io.File;
import java.util.List;

public interface LessonServiceInt {
    Long addLesson(Lesson lesson);
    Lesson getLesson(Long id);
    void updateLesson (Lesson lesson);
    void deleteLesson(Lesson lesson);
    List<Lesson> getLessons();
    List<String> getParametersInString(Lesson lesson);
    List<String> getLessonsOnSubject(Subjects subjects);
    List<Lesson> getLessonsOnSubjectList(Subjects subjects);
    List<String> getLessonsOnType(LessonType type);
    List<Lesson> getLessonsOnTypeList(LessonType type);
}
