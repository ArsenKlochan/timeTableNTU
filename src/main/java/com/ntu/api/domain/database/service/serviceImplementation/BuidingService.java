package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.BuildingDAOInt;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.service.serviceInterface.BuildingServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BuidingService implements BuildingServiceInt {
    @Autowired
    private BuildingDAOInt buildingDAO;

    @Override
    public Long addBuilding(Building building) {
        return buildingDAO.create(building);
    }

    @Override
    public Building getBuilding(Long id) {
        return buildingDAO.get(id);
    }

    @Override
    public void updateBuilding(Building building) {
        buildingDAO.update(building);
    }

    @Override
    public void deleteBuilding(Building building) {
        buildingDAO.delete(building);
    }

    @Override
    public List<Building> getBuildingList() {
        return buildingDAO.findAll();
    }
}
