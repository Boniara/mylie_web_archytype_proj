package com.bavuta.service;

import com.bavuta.db.dao.UserDAO;
import com.bavuta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by bogdan on 4/27/2017.
 */

@Service
public class UserService extends AbstractService {
    
    @Autowired
    private UserDAO userDAO;

    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user) {
        userDAO.createUser(user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User getUserById(Long id) {
        User user = userDAO.getUserById(id);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User getUserByUsername(String username) {
        User user = userDAO.getUserByUsername(username);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<User> getUserList() {
        List<User> userList = userDAO.getUserList();
        return userList;
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        userDAO.updateUser(user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public long deleteUserById(long id) {
        userDAO.deleteUser(id);
        return id;
    }
}
