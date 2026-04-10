package com.system.service;

import com.system.model.User;
import com.system.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserRepository repository = Mockito.mock(UserRepository.class);
    private UserService service = new UserService(
        repository,
        new BCryptPasswordEncoder()
    );

    @Test
    void shouldFindUserByUsername() {

        User user = new User( "admin", "admin", "ROLE_ADMIN");

        Mockito.when(repository.findByUsername("admin")).thenReturn(user);

        User found = service.findByUsername("admin");

        assertEquals("admin", found.getUsername());
    }
    @Test
    void shouldReturnNullIfUserNotFound() {

        Mockito.when(repository.findByUsername("x")).thenReturn(null);

        User result = service.findByUsername("x");

        assertNull(result);
    }
}