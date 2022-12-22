package com.techinc.coffeehouse.repository;

import com.techinc.coffeehouse.entity.User;
import java.util.List;

public interface UserDao {
    User getUserById(int userId);

    void addUser(User user);

    void removeUserById(int userId);

    List<User> getAllUsers();
}
