package com.springboot.demo.view;

import com.springboot.demo.entity.UserEntity;

public class UserView {
    private final Integer id;
    private final String name;
    private final String email;

    public UserView(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
