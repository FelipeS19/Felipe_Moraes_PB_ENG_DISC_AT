package com.system.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.model.User;
import com.system.repository.UserRepository;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    public User findByUsername(String username){
        return repository.findByUsername(username);
    }

    public List<User> listUsers(){
        return repository.findAll();
    }

    public User save(User user){
        User newUser = new User(
            user.getUsername(),
            encoder.encode(user.getPassword()),
            user.getRole()
        );
        return repository.save(newUser);
    }
}