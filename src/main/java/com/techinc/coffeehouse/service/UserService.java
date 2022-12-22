package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.User;

public interface UserService {
    User getUserById(int userId);

    void addUser(User user);
}
