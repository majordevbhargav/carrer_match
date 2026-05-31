package com.example.helloapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.helloapp.dto.LoginRequest;
import com.example.helloapp.dto.LoginResponse;
import com.example.helloapp.entity.User;
import com.example.helloapp.security.JwtService;
import com.example.helloapp.service.OtpService;
import com.example.helloapp.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OtpService otpService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        User user =
                userService.login(
                        request.getEmail(),
                        request.getPassword());

        if (user == null) {

            return new LoginResponse(
                    "Invalid Credentials");
        }

        if (!otpService.isEmailVerified(
                request.getEmail())) {

            return new LoginResponse(
                    "Please verify your email before login");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail());

        return new LoginResponse(
                token);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to Career Match Dashboard";
    }
}