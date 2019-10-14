package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.Lists;
import com.ntu.api.domain.database.dao.DAOImplementation.DepartmentDAO;
import com.ntu.api.domain.database.dao.DAOinterface.BuildingDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.ClassRoomDAOInt;
import com.ntu.api.domain.database.dao.DAOinterface.DepartmentDAOInt;
import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Department;
import com.ntu.api.domain.database.entity.enums.ClassRoomTypes;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
import com.ntu.api.model.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
        for (ClassRoom classRoom: departmentDAO.get(department.getDepartmentId()).getClassRooms()){
            classRoomsList.add(classRoom.getClassRoomName());
        }
        return classRoomsList;
    }

    @Override
    public List<String> getClassRoomsOnBuildin(Building building) {
        List<String> classRoomsList = new ArrayList<>();
        for (ClassRoom classRoom: buildingDAO.get(building.getBuildingId()).getClassRooms()){
            classRoomsList.add(classRoom.getClassRoomName());
        }
        return classRoomsList;
    }

    @Override
    public List<String> getClassRoomsOnType(ClassRoomTypes type) {
        List<String> classRoomsList = new ArrayList<>();
        for (ClassRoom classRoom: classRoomDAO.findAll()){
            if(classRoom.getType().name().equals(type.name())) {
                classRoomsList.add(classRoom.getClassRoomName());
            }
        }
        return classRoomsList;
    }

    @Override
    public List<ClassRoom> getClassRoomsOnTypeList(ClassRoomTypes types) {
        List<ClassRoom> classRoomList = new ArrayList<>();
        for (ClassRoom classRoom: classRoomDAO.findAll()){
            if(classRoom.getType().name().equals(types.name())) {
                classRoomList.add(classRoom);
            }
        }
        return classRoomList;
    }

    @Override
    public List<ClassRoom> getClassRoomsOnDepartmentsList(Department department) {
        List<ClassRoom> classRoomList = new ArrayList<>();
        for (ClassRoom classRoom: departmentDAO.get(department.getDepartmentId()).getClassRooms()){
            classRoomList.add(classRoom);
        }
        return classRoomList;
    }
    @Override
    public List<ClassRoom> getClassRoomsOnBuildinList(Building building) {
        List<ClassRoom> classRoomList = new ArrayList<>();
        for (ClassRoom classRoom: buildingDAO.get(building.getBuildingId()).getClassRooms()){
            classRoomList.add(classRoom);
        }
        return classRoomList;
    }

    @Override
    public void addClassRoomFromFile(File file) {
        for(ArrayList<String> list: ExcelReader.excelRead(file.getAbsolutePath())){
            String name = list.get(0);
            int size = Integer.parseInt(list.get(1));
            addClassRoom(new ClassRoom(name, size, ClassRoomTypes.valueOf(list.get(2)),
               Lists.getBuildingService().getBuildingList().get(Lists.getBuildingList().indexOf(list.get(3))),
               Lists.getDepartmentService().getDepartments().get(Lists.getDepartmentNameList().indexOf(list.get(4)))));
        }
    }
}
