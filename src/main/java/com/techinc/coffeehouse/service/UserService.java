package com.techinc.coffeehouse.service;

import com.techinc.coffeehouse.entity.User;
import com.techinc.coffeehouse.exception.UserNotFoundException;
import com.techinc.coffeehouse.repository.UserDao;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(int userId) {
        Optional<User> optUser = userDao.getEntityById(userId);
        return optUser.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " does not exist"));
    }

    public void addUser(User user) {
        userDao.addEntity(user);
    }

    public void removeUser(User user) {
        userDao.removeEntity(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllEntities();
    }
}
