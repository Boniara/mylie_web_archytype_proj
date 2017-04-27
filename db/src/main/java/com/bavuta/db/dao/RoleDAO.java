package com.bavuta.db.dao;

import com.bavuta.model.Role;

import java.util.List;

/**
 * Created by bogdan on 4/27/2017.
 */
public interface RoleDAO {

    void createRole(Role role);
    Role getRoleById(long id);
    List<Role> getRoleList();
    void updateRole(Role role);
    void deleteRole(long id);
}
