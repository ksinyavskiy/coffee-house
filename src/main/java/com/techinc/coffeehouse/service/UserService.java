package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.User;
import java.util.List;

public interface UserService {
    User getUserById(int userId);

    void addUser(User user);

    void removeUser(int userId);

    List<User> getAllUsers();
}
