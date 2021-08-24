package com.ccj.service;

import com.ccj.domain.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    List<User> list();

    void save(User user, Long[] roleIds);

    void del(Long userId);

    User login(User user);
}
