package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.ClassRoomDAOInt;
import com.ntu.api.domain.database.entity.ClassRoom;
import com.ntu.api.domain.database.service.serviceInterface.ClassRoomServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ClassRoomService implements ClassRoomServiceInt {
    @Autowired
    private ClassRoomDAOInt classRoomDAO;

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
}
