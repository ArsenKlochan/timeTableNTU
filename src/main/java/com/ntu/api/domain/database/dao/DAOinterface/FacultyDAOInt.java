package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Faculty;

import java.util.List;

public interface FacultyDAOInt {
    Long create(Faculty faculty);

    Faculty get (Long id);

    void update(Faculty faculty);

    void delete(Faculty faculty);

    List<Faculty> findAll();
}

