package com.bavuta.db.dao;

import com.bavuta.model.Role;
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
public class RoleDAOTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private RoleDAO roleDAO;

    private Role role = new Role() {
        {
            setRole(ROLE.USER_ROLE);
        }
    };

    @Test
    public void createRoleTest() {
        roleDAO.createRole(role);
        Assert.assertNotNull(role.getId(), "Role did not create");
    }

    @Test(dependsOnMethods = {"createRoleTest"})
    public void getRoleByIdTest() {
        Role selectedRole = roleDAO.getRoleById(role.getId());
        checkRole(selectedRole);
    }

    @Test(dependsOnMethods = {"getRoleByIdTest"})
    public void getRoleListTest() {
        List<Role> roleList = roleDAO.getRoleList();
        checkRole(roleList.get(roleList.size() - 1));
    }

    @Test(dependsOnMethods = {"getRoleListTest"})
    public void updateRoleTest() {
        role.setRole(Role.ROLE.ADMIN_ROLE);
        roleDAO.updateRole(role);
        Role selectedRole = roleDAO.getRoleById(role.getId());
        checkRole(selectedRole);
    }

    @Test(dependsOnMethods = {"updateRoleTest"})
    public void deleteRoleTest() {
        roleDAO.deleteRole(role.getId());
        Assert.assertNull(roleDAO.getRoleById(role.getId()));
    }

    private void checkRole(Role selectedRole) {
        Assert.assertEquals(role.getId(), selectedRole.getId(), "Id doesn`t match");
    }
}
