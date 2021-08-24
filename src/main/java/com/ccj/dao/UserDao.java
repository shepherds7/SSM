package com.ccj.dao;

import com.ccj.domain.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserDao {
    List<User> findAll();

    Long save(User user);

    void saveUserRoleRel(Long id, Long[] roleIds);

    void delUserRoleRel(Long userId);

    void del(Long userId);

    User login(User user);
}
