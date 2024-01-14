package com.springboot.demo.service.impl;

import com.springboot.demo.entity.UserEntity;
import com.springboot.demo.form.UserLoginForm;
import com.springboot.demo.form.UserRegisterForm;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.security.JwtGenerator;
import com.springboot.demo.service.CustomUserDetailsService;
import com.springboot.demo.service.UserService;
import com.springboot.demo.utils.UserType;
import com.springboot.demo.utils.exception.BadRequestException;
import com.springboot.demo.view.LoginView;
import com.springboot.demo.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtGenerator jwtGenerator;
    @Override
    public UserView Register(UserRegisterForm userRegisterForm) {
        UserEntity userEntity = userRepository.findByEmail(userRegisterForm.getEmail());
        if (userEntity != null) {
            throw  badRequestException("User already exist");
        }
        userEntity = new UserEntity();
        userEntity.setEmail(userRegisterForm.getEmail());
        userEntity.setName(userRegisterForm.getName());
        userEntity.setPassword(passwordEncoder.encode(userRegisterForm.getPassword()));
        userEntity.setStatus(userRegisterForm.getStatus());
        userRepository.save(userEntity);
        return new UserView(userEntity);

    }

    @Override
    public LoginView Login(UserLoginForm userLoginForm){
        UserEntity userEntity=userRepository.findByEmailAndStatusNot(userLoginForm.getEmail(),0);
        if (userEntity == null){
            throw badRequestException("User Not Found");
        }
        if (!passwordEncoder.matches(userLoginForm.getPassword(), userEntity.getPassword())) {
            throw badRequestException("Invalid Credential");
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginForm.getEmail(), userLoginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token="";
        if (userEntity.getStatus() == 1){
            customUserDetailsService.setUserType(UserType.ADMIN);
            token = jwtGenerator.generateToken(authentication,UserType.ADMIN.toString());
        } else if (userEntity.getStatus() == 2) {
            customUserDetailsService.setUserType(UserType.USER);
            token = jwtGenerator.generateToken(authentication,UserType.USER.toString());
        }
        return new LoginView(userEntity,token);
    }

    @Override
    public Collection<UserEntity> AdminList(){
        return userRepository.findByStatus(1);
    }
    @Override
    public Collection<UserEntity> UserList(){
        return userRepository.findByStatus(2);
    }
    private static BadRequestException badRequestException(String reason) {
        return new BadRequestException(reason);
    }
}
