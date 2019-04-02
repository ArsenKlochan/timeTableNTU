package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.ClassRoom;

import java.util.List;

public interface ClassRoomDAOInt {
    Long create(ClassRoom classRoom);

    ClassRoom get (Long id);

    void update(ClassRoom classRoom);

    void delete(ClassRoom classRoom);

    List<ClassRoom> findAll();
}
