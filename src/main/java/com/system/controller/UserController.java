package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.system.service.UserService;
import com.system.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("users", service.listUsers());
        return "user";
    }

    @GetMapping("/new")
    public String createForm(Model model){
        model.addAttribute("user", new User(null, null, null));
        return "user-form";
    }

    @PostMapping
    public String save(@ModelAttribute User user){
        service.save(user);
        return "redirect:/user";
    }

}