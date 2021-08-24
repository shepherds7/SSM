package com.ccj.service.impl;

import com.ccj.dao.RoleDao;
import com.ccj.dao.UserDao;
import com.ccj.domain.Role;
import com.ccj.domain.User;
import com.ccj.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserServiceImpl implements UserService {


    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<User> list() {
        List<User> userList = userDao.findAll();

        //封装userList中的每一个User的roles数据
        for (User user : userList) {
            //获得user的id
            Long id = user.getId();
            //将id作为参数 查询当前userid对应的Role集合的数据
            List<Role> roles = roleDao.findRoleByUserId(id);
            user.setRoles(roles);
        }

        return userList;
    }

    @Override
    public void save(User user, Long[] roleIds) {
        //第一步 向sys_user表中存储数据
        Long userId = userDao.save(user);

        //第二步 向sys_user_role 关系表中存储多条数据
        userDao.saveUserRoleRel(userId, roleIds);
    }

    @Override
    public void del(Long userId) {
        //1.删除关系表sys_user_role
        userDao.delUserRoleRel(userId);

        //2.删除sys_user表
        userDao.del(userId);
    }

    @Override
    public User login(User user) {
       User loginUser =  userDao.login(user);
       return loginUser;
    }
}
