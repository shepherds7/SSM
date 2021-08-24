package com.ccj.controller;


import com.ccj.domain.Role;
import com.ccj.domain.User;
import com.ccj.service.RoleService;
import com.ccj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public ModelAndView list() {
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(User user,Long[] roleIds) {
        userService.save(user,roleIds);

        return "redirect:/user/list";

    }


    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId) {
        userService.del(userId);

        return "redirect:/user/list";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session){
      User loginUser =  userService.login(user);
      if (loginUser != null){
          session.setAttribute("user",user);
          return "main";
      }else {
          return "failer";
      }

    }

}
