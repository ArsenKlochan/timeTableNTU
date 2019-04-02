package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Group;

import java.util.List;

public interface GroupDAOInt {
    Long create(Group group);

    Group get (Long id);

    void update(Group group);

    void delete(Group group);

    List<Group> findAll();
}
