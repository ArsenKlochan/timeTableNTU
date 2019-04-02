package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.ClassRoom;

import java.util.List;

public interface ClassRoomServiceInt {
    Long addClassRoom(ClassRoom classRoom);
    ClassRoom getClassRoom(Long id);
    void updateClassRoom(ClassRoom classRoom);
    void deleteClassRoom(ClassRoom classRoom);
    List<ClassRoom> getClassRoomList();
}
