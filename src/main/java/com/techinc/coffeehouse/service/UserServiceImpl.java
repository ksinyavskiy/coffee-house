package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.repository.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void removeUser(int userId) {
        userDao.removeUserById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
