package com.m4gpie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.m4gpie.dto.UserDto;
import com.m4gpie.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    // mock user admin admin
    private List<User> users = new ArrayList<>(List
            .of(new User("0", "Admin", "$2a$12$KPP9VLvgMj4gvKd7iLGbhOtbN34R/uwR6DqPi0aFZ05TbtKkMrEnG")));

    public String loginUser(UserDto userDto) {

        User user = users.stream().filter(u -> u.getUsername().equals(userDto.username())).findFirst().orElse(null);

        if (user != null) {
            if (encoder.matches(userDto.password(), user.getPassword())) {
                return jwtService.generateToken(user);
            } else {
                return "Wrong password";
            }
        } else {
            return "User not found";
        }
    }

    public String regiserUser(UserDto userDto) {
        User user = userFromDto(userDto);
        users.add(user);
        return jwtService.generateToken(user);
    }

    private User userFromDto(UserDto user) {
        return new User(Integer.toString(users.size()), user.username(), encoder.encode(user.password()));
    }

}
