package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.UserModel;
import com.example.demo.ResponseEntity.Response;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Object signup(@RequestBody UserModel user) {
        // Response class handles HTTP status and body
        return Response.success("User registered successfully", userService.registerUser(user));
    }

    @PostMapping("/login")
    public Object login(@RequestBody UserModel user) {
        boolean authenticated = userService.authenticateUser(user.getEmail(), user.getPassword());

        if (!authenticated) {
            return Response.unauthorized("Invalid Credentials");
        }

        return Response.success("Login Successful", null);
    }
}
