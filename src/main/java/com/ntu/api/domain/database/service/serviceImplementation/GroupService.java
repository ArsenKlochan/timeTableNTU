package com.ntu.api.domain.database.service.serviceImplementation;

import com.ntu.api.domain.database.dao.DAOinterface.GroupDAOInt;
import com.ntu.api.domain.database.entity.*;
import com.ntu.api.domain.database.service.serviceInterface.GroupServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService implements GroupServiceInt {
    @Autowired
    private GroupDAOInt groupDAO;

    @Override
    public Long addGroupe(Group groupe) {
        return groupDAO.create(groupe);
    }
    @Override
    public Group getGroupe(Long id) {
        return groupDAO.get(id);
    }
    @Override
    public void updateGroupe(Group groupe) {
        groupDAO.update(groupe);
    }
    @Override
    public void deleteGroupe(Group groupe) {
        groupDAO.delete(groupe);
    }
    @Override
    public List<Group> getGroups() {
        return groupDAO.findAll();
    }
}
