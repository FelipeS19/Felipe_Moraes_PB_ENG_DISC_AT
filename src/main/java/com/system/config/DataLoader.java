package com.system.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.system.model.User;
import com.system.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository repository , PasswordEncoder encoder) {

        return args -> {

            try {
                if(repository.count() == 0){

                    User admin = new User("admin", encoder.encode("admin"), "ROLE_ADMIN");
              
                    User user = new User("user", encoder.encode("123"), "ROLE_USER");

                    repository.save(admin);
                    repository.save(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}