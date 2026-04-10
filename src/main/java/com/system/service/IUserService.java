package com.system.service;

import java.util.List;

import com.system.model.User;

public interface IUserService {
    User findByUsername(String username);
    List<User> listUsers();
    
}
