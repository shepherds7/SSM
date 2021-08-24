package com.ccj.service.impl;

import com.ccj.dao.RoleDao;
import com.ccj.domain.Role;
import com.ccj.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;


public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> list() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
}
