package com.ntu.api.domain.database.dao.DAOImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.ClassRoomDAOInt;
import com.ntu.api.domain.database.entity.ClassRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ClassRoomDAO implements ClassRoomDAOInt {
    @Autowired
    private SessionFactory factory;

    @Override
    public Long create(ClassRoom classRoom) {
        return (Long)factory.getCurrentSession().save(classRoom);
    }

    @Override
    public ClassRoom get(Long id) {
        return factory.getCurrentSession().get(ClassRoom.class, id);
    }

    @Override
    public void update(ClassRoom classRoom) {
        factory.getCurrentSession().update(classRoom);
    }

    @Override
    public void delete(ClassRoom classRoom) {
        factory.getCurrentSession().delete(classRoom);
    }

    @Override
    public List<ClassRoom> findAll() {
        return factory.getCurrentSession().createQuery("from ClassRoom").list();
    }
}
