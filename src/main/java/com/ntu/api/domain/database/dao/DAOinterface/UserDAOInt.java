package com.ntu.api.domain.database.dao.DAOinterface;

import com.ntu.api.domain.database.entity.User;

import java.util.List;

public interface UserDAOInt {
    Long create(User user);

    User get (Long id);

    void update(User user);

    void delete(User user);

    List<User> findAll();
}
