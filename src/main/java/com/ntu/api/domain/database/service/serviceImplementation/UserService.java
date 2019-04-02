package com.ntu.api.domain.database.service.serviceImplementation;


import com.ntu.api.domain.database.dao.DAOinterface.UserDAOInt;
import com.ntu.api.domain.database.entity.User;
import com.ntu.api.domain.database.service.serviceInterface.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements UserServiceInt {
    @Autowired
    private UserDAOInt userDAO;

    @Override
    public Long addUser(User user) {
        return userDAO.create(user);
    }
    @Override
    public User getUser(Long id) {
        return userDAO.get(id);
    }
    @Override
    public void updateUser(User user) {
        userDAO.update(user);
    }
    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public boolean authentUser(User user) {
        for (User use : userDAO.findAll()) {
            if (use.getLogin().equals(user.getLogin()) && use.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addUserComplite(User user) {
        if (check(user)) {
            userDAO.create(user);
            return true;
        } else {
            return false;
        }
    }

    private boolean check(User user) {
        for (User users : userDAO.findAll()) {
            if (users.getLogin().equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }
}
