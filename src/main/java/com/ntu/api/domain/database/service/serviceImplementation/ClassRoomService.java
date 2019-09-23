package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOImplementation.DepartmentDAO;
import com.ntu.api.domain.database.dao.DAOinterface.BuildingDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.ClassRoomDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class ClassRoomService implements ClassRoomServiceInt {
    @Autowired
    private ClassRoomDAOInt classRoomDAO;
    @Autowired
    private DepartmentDAOInt departmentDAO;
    @Autowired
    private BuildingDAOInt buildingDAO;

    @Override
    public Long addClassRoom(ClassRoom classRoom) {
        return classRoomDAO.create(classRoom);
    }

    @Override
    public ClassRoom getClassRoom(Long id) {
        return classRoomDAO.get(id);
    }

    @Override
    public void updateClassRoom(ClassRoom classRoom) {
        classRoomDAO.update(classRoom);
    }

    @Override
    public void deleteClassRoom(ClassRoom classRoom) {
        classRoomDAO.delete(classRoom);
    }

    @Override
    public List<ClassRoom> getClassRoomList() {
        return classRoomDAO.findAll();
    }
    @Override
    public List<String> getParametersInString(ClassRoom classRoom){
        List<String> parameters = new ArrayList<>();
        Building building = buildingDAO.get(classRoom.getBuilding().getBuildingId());
        Department department = departmentDAO.get(classRoom.getDepartment().getDepartmentId());
        parameters.add(classRoom.getClassRoomName());
        parameters.add(classRoom.getClassRoomSize().toString());
        parameters.add(classRoom.getType().name());
        parameters.add(building.getBuildingName());
        parameters.add(department.getDepartmentCode() + " " + department.getDepartmentName());
        return parameters;
    }

    @Override
    public List<String> getClassRoomsOnDepartments(Department department) {
        List<String> classRoomsList = new ArrayList<>();
        List<ClassRoom> classRooms = classRoomDAO.findAll();
        Department tempDepartment;
        for (ClassRoom classRoom: classRooms){
            tempDepartment = departmentDAO.get(classRoom.getDepartment().getDepartmentId());
            if(tempDepartment.getDepartmentId() == department.getDepartmentId()){
                classRoomsList.add(classRoom.getClassRoomName());
            }
        }
        return classRoomsList;
    }

    @Override
    public List<String> getClassRoomsOnBuildin(Building building) {
        List<String> classRoomsList = new ArrayList<>();
        List<ClassRoom> classRooms = classRoomDAO.findAll();
        Building tempBuilding;
        for (ClassRoom classRoom:classRooms){
            tempBuilding = buildingDAO.get(classRoom.getBuilding().getBuildingId());
            if(tempBuilding.getBuildingId() == building.getBuildingId()){
                classRoomsList.add(classRoom.getClassRoomName());
            }
        }
        return classRoomsList;
    }

    @Override
    public List<ClassRoom> getClassRoomsOnDepartmentsList(Department department) {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        List<ClassRoom> classRooms = classRoomDAO.findAll();
        Department tempDepartment;
        for (ClassRoom classRoom: classRooms) {
            tempDepartment = departmentDAO.get(classRoom.getDepartment().getDepartmentId());
            if(tempDepartment.getDepartmentId() == department.getDepartmentId()){
                classRoomsList.add(classRoom);
            }
        }
        return classRoomsList;
    }

    @Override
    public List<ClassRoom> getClassRoomsOnBuildinList(Building building) {
        List<ClassRoom> classRoomsList = new ArrayList<>();
        List<ClassRoom> classRooms = classRoomDAO.findAll();
        Building tempBuilding;
        for (ClassRoom classRoom: classRooms) {
            tempBuilding = buildingDAO.get(classRoom.getBuilding().getBuildingId());
            if(tempBuilding.getBuildingId() == building.getBuildingId()){
                classRoomsList.add(classRoom);
            }
        }
        return classRoomsList;
    }


}
