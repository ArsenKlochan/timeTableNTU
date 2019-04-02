package com.ntu.api.domain.database.service.serviceInterface;

import com.ntu.api.domain.database.entity.User;

import java.util.List;

public interface UserServiceInt {
    Long addUser(User user);
    User getUser(Long id);
    void updateUser (User user);
    void deleteUser (User user);
    List<User> getUsers();
    boolean addUserComplite(User user);
    boolean authentUser(User user);
}
