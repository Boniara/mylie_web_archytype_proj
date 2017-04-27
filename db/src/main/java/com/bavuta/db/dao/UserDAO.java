package com.bavuta.db.dao;

import com.bavuta.model.User;
import java.util.List;

/**
 * Created by bogdan on 4/27/2017.
 */
public interface UserDAO {

    void createUser(User user);
    User getUserById(long id);
    List<User> getUserList();
    void updateUser(User user);
    void deleteUser(long id);

}
