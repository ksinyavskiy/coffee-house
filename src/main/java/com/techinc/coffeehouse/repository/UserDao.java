package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;

public interface UserDao {
    User getUserById(int userId);

    void addUser(User user);
}
