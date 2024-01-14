package com.springboot.demo.service;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.form.UserLoginForm;
import com.springboot.demo.form.UserRegisterForm;
import com.springboot.demo.view.LoginView;
import com.springboot.demo.view.UserView;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface UserService {

    UserView Register(UserRegisterForm userRegisterForm);
    LoginView Login(UserLoginForm userLoginForm);
    Collection<UserEntity> AdminList();
    Collection<UserEntity> UserList();

}
