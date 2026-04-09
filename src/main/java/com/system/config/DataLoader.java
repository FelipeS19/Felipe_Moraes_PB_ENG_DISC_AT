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

                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setPassword(encoder.encode("admin"));
                    admin.setRole("ROLE_ADMIN");        
                    System.out.println("Admin criado: " + admin.getUsername() + " / " + admin.getPassword());

                    User user = new User();
                    user.setUsername("user");
                    user.setPassword(encoder.encode("123"));
                    user.setRole("ROLE_USER");
                    System.out.println("User criado: " + user.getUsername() + " / " + user.getPassword());

                    repository.save(admin);
                    repository.save(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
    }
}