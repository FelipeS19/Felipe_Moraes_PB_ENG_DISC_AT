package com.system.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.system.model.User;
import com.system.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository repository){

        return args -> {

            try {
                if(repository.count() == 0){

                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setPassword("{noop}admin");
                    admin.setRole("ROLE_ADMIN");

                    User user = new User();
                    user.setUsername("user");
                    user.setPassword("{noop}123");
                    user.setRole("ROLE_USER");

                    repository.save(admin);
                    repository.save(user);
                }
            } catch (Exception e) {

            }

        };
    }
}