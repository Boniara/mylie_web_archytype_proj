package com.bavuta.service;

import com.bavuta.db.dao.RoleDAO;
import com.bavuta.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bogdan on 4/27/2017.
 */

@Service
public class RoleService extends AbstractService {

    @Autowired
    private RoleDAO roleDAO;

    @Transactional(rollbackFor = Exception.class)
    public Role createRole(Role role) {
        roleDAO.createRole(role);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    public Role getRoleById(Long id) {
        Role role = roleDAO.getRoleById(id);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Role> getRoleList() {
        List<Role> roleList = roleDAO.getRoleList();
        return roleList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Role updateRole(Role role) {
        roleDAO.updateRole(role);
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    public long deleteRoleById(long id) {
        roleDAO.deleteRole(id);
        return id;
    }
}
