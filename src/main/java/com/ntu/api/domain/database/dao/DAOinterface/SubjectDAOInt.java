package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Subjects;

import java.util.List;

public interface SubjectDAOInt {
    Long create(Subjects subject);

    Subjects get (Long id);

    void update(Subjects subject);

    void delete(Subjects subject);

    List<Subjects> findAll();
}
