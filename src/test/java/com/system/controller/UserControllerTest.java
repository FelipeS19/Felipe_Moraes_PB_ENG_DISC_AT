package com.system.controller;

import com.system.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserService service = Mockito.mock(UserService.class);
    private UserController controller = new UserController(service);

    @Test
    void shouldReturnUsersPage() {
        Model model = Mockito.mock(Model.class);

        String view = controller.list(model);

        assertEquals("user", view);
        Mockito.verify(service).listUsers();
    }
}