package com.example.newsportal.controller;

import com.example.newsportal.model.*;
import com.example.newsportal.service.RoleService;
import com.example.newsportal.service.UserService;
import com.example.newsportal.service.impl.RoleServiceImpl;
import com.example.newsportal.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final RoleServiceImpl roleService;
    private final UserServiceImpl userService;

    public UserController(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    //    Login page settings

    @GetMapping("/login")
    public String loginAsUser(Model model) {
        return "login";
    }


//    UserRegister page settings

    @GetMapping("/userRegister")
    public String userRegister(Model model) {
        model.addAttribute("newUser", new User());
        return "userRegister";
    }

    @PostMapping("/userRegister")
    public String saveUser(@ModelAttribute("newUser") User user) {
        roleService.setUserRole(user);
        userService.saveUser(user);
        return "index";
    }

}
