package com.springboot.demo.controller;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.form.UserLoginForm;
import com.springboot.demo.form.UserRegisterForm;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.service.UserService;
import com.springboot.demo.view.LoginView;
import com.springboot.demo.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @PostMapping("api/register")
    public UserView Register(@RequestBody UserRegisterForm userRegisterForm) {
        return userService.Register(userRegisterForm);
    }

    @PostMapping("api/login")
    public LoginView login(@RequestBody UserLoginForm userLoginForm) {
        return userService.Login(userLoginForm);
    }

    @GetMapping("api/adminList")
    public Collection<UserEntity> AdminList(){
        return userService.AdminList();
    }

    @GetMapping("api/userList")
    public Collection<UserEntity> UserList(){
        return userService.UserList();
    }

}
