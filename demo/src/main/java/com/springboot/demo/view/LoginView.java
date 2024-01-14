package com.springboot.demo.view;

import com.springboot.demo.entity.UserEntity;

public class LoginView extends UserView{
    private final String token;


    public LoginView(UserEntity userEntity, String token) {
        super(userEntity);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
