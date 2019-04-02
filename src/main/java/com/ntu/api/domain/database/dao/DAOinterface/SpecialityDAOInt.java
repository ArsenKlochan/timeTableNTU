package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Speciality;

import java.util.List;

public interface SpecialityDAOInt {
    Long create(Speciality speciality);

    Speciality get (Long id);

    void update(Speciality speciality);

    void delete(Speciality speciality);

    List<Speciality> findAll();
}
