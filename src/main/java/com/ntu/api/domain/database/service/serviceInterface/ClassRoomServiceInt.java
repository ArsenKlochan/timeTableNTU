package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.Building;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.entity.Department;

import java.io.File;
import java.util.List;

public interface ClassRoomServiceInt {
    Long addClassRoom(ClassRoom classRoom);
    ClassRoom getClassRoom(Long id);
    void updateClassRoom(ClassRoom classRoom);
    void deleteClassRoom(ClassRoom classRoom);
    List<ClassRoom> getClassRoomList();
    List<String> getParametersInString(ClassRoom classRoom);
    List<String> getClassRoomsOnDepartments(Department department);
    List<String> getClassRoomsOnBuildin(Building building);
    List<ClassRoom> getClassRoomsOnDepartmentsList(Department department);
    List<ClassRoom> getClassRoomsOnBuildinList(Building building);
    void addClassRoomFromFile(File file);
}
