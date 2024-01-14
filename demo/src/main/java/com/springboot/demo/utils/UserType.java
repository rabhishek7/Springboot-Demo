package com.springboot.demo.utils;

public enum UserType {
    ADMIN("ADMIN"), TEACHER("TEACHER"), USER("USER");

    private final String type;

    UserType(String string) {
        type = string;
    }

    @Override
    public String toString() {
        return type;
    }
}
