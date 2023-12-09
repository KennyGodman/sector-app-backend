package com.sector.controller;

import com.sector.request.LoginRequest;
import com.sector.request.UserRegistrationRequest;
import com.sector.response.UserResponse;
import com.sector.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/sign-up")
    public UserResponse signUp(@Valid @RequestBody UserRegistrationRequest request){
       return userService.createUser(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest request){
        return userService.login(request);
    }
}
