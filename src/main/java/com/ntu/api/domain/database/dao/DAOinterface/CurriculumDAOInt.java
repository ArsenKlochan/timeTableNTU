package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Curriculum;

import java.util.List;

public interface CurriculumDAOInt {
    Long create(Curriculum curriculum);

    Curriculum get (Long id);

    void update(Curriculum curriculum);

    void delete(Long id);

    List<Curriculum> findAll();
}
