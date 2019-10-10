package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Building;

import java.io.File;
import java.util.List;

public interface BuildingServiceInt {
    Long addBuilding(Building building);
    Building getBuilding(Long id);
    void updateBuilding (Building building);
    void deleteBuilding(Building building);
    List< Building> getBuildingList();
    void addBuildingFromFile(File file);
}
