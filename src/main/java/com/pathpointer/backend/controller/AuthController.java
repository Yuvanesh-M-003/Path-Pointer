package com.pathpointer.backend.controller;

import com.pathpointer.backend.model.User;
import com.pathpointer.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/google")
    public User googleLogin(@RequestBody Map<String, String> body) {

        String email = body.get("email");

        User user = userService.findOrCreateUser(email);

        return user;
    }
}