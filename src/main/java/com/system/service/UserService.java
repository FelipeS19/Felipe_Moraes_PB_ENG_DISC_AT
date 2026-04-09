package com.system.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.system.model.User;
import com.system.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }
    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public List<User> listUsers(){
        return repository.findAll();
    }

}