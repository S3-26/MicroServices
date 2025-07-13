package com.example.UserService.services;

import com.example.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUser(String id);

}
