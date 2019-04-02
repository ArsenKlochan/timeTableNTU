package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Teacher;

import java.util.List;

public interface TeacherDAOInt {
    Long create(Teacher teacher);

    Teacher get (Long id);

    void update(Teacher teacher);

    void delete(Teacher teacher);

    List<Teacher> findAll();
}
