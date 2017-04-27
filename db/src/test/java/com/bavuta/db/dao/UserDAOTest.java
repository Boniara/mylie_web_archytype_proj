package com.bavuta.db.dao;

import com.bavuta.model.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by bogdan on 4/27/2017.
 */

@Test
@ContextConfiguration("classpath*:db-test.xml")
public class UserDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDAO userDAO;

    private User user = new User() {
        {
            setFirstName("f1");
            setLastName("l1");
            setEmail("e1" + Math.random() + "@e.e");
        }
    };

    @Test
    public void createUserTest() {
        userDAO.createUser(user);
        Assert.assertNotNull(user.getId(), "User did not create");
    }

    @Test(dependsOnMethods = {"createUserTest"})
    public void getUserByIdTest() {
        User selectedUser = userDAO.getUserById(user.getId());
        checkUser(selectedUser);
    }

    @Test(dependsOnMethods = {"getUserByIdTest"})
    public void getUserListTest() {
        List<User> userList = userDAO.getUserList();
        checkUser(userList.get(userList.size() - 1));
    }

    @Test(dependsOnMethods = {"getUserListTest"})
    public void updateUserTest() {
        user.setFirstName("f2");
        user.setLastName("l2");
        user.setEmail("e2" + Math.random() + "@e.e");
        userDAO.updateUser(user);
        User selectedUser = userDAO.getUserById(user.getId());
        checkUser(selectedUser);
    }

    @Test(dependsOnMethods = {"updateUserTest"})
    public void deleteUserTest() {
        userDAO.deleteUser(user.getId());
        Assert.assertNull(userDAO.getUserById(user.getId()));
    }

    private void checkUser(User selectedUser) {
        Assert.assertEquals(user.getId(), selectedUser.getId(), "Id doesn`t match");
        Assert.assertEquals(user.getFirstName(), selectedUser.getFirstName(), "First name doesn`t match");
        Assert.assertEquals(user.getLastName(), selectedUser.getLastName(), "Last name doesn`t match");
        Assert.assertEquals(user.getEmail(), selectedUser.getEmail(), "Email doesn`t match");
    }
}
