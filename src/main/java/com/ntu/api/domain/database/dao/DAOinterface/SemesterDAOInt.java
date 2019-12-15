package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Semester;

import java.util.List;

public interface SemesterDAOInt {
    Long create(Semester semester);

    Semester get (Long id);

    void update(Semester semester);

    void delete(Semester semester);

    List<Semester> findAll();
}
