package com.example.newsportal.controller;

import com.example.newsportal.model.*;
import com.example.newsportal.repository.UserRepository;
import com.example.newsportal.service.RoleService;
import com.example.newsportal.service.UserService;
import com.example.newsportal.service.impl.RoleServiceImpl;
import com.example.newsportal.service.impl.UserServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final RoleServiceImpl roleService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    public UserController(RoleServiceImpl roleService, UserServiceImpl userService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userService = userService;
        this.userRepository = userRepository;
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

    @GetMapping("/user/{id}")
    public String user(@PathVariable("id") Long id, Model model) {
        User user1 = userRepository.findById(id).get();
        model.addAttribute("speaker", user1);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/account/{id}")
    public String account(@PathVariable("id") Long id, Model model){
        model.addAttribute("meetings",userRepository.findById(id).get().getMeetings());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "account";
    }

}
