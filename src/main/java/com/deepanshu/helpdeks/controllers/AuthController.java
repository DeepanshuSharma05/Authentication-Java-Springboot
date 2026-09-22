package com.deepanshu.helpdeks.controllers;

import com.deepanshu.helpdeks.dto.UserLoginDto;
import com.deepanshu.helpdeks.dto.UserRegistrationDto;
import com.deepanshu.helpdeks.entities.User;
import com.deepanshu.helpdeks.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")

public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserRegistrationDto userRegistrationDto){
        User registeredUser = userService.registerUser(userRegistrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginDto userLoginDto){
        String token = userService.loginUser(userLoginDto);
        return ResponseEntity.ok(token);
    }

}
