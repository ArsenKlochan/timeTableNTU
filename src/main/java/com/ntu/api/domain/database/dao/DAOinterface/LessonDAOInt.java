package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Lesson;

import java.util.List;

public interface LessonDAOInt {
    Long create(Lesson lesson);

    Lesson get (Long id);

    void update(Lesson lesson);

    void delete(Lesson lesson);

    List<Lesson> findAll();
}
