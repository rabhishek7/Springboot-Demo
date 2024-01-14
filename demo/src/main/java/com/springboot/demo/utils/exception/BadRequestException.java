package com.springboot.demo.utils.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);

    }
}

