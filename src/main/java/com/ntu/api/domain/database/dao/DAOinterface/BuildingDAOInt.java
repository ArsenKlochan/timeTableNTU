package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.Building;

import java.util.List;

public interface BuildingDAOInt {
    Long create(Building building);

    Building get (Long id);

    void update(Building building);

    void delete(Building building);

    List<Building> findAll();
}
