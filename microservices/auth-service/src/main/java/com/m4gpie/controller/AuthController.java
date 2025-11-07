package com.m4gpie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m4gpie.dto.UserDto;
import com.m4gpie.service.JWTService;
import com.m4gpie.service.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        return userService.loginUser(user);

    }

    @PostMapping("/register")
    public String register(@RequestBody UserDto user) {
        return userService.regiserUser(user);
    }

    @PostMapping("/verifyToken")
    public Boolean postMethodName(@RequestBody Map<String, String> body) {

        String token = body.get("token");

        return jwtService.isValid(token);
    }

}
