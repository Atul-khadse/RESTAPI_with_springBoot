package com.SpringToDatabase_JPA.SpringToDatabase_JPA.controller;


import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.CreateUserDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.LoginDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.LoginResponseDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.dto.RegisterUserResponseDto;
import com.SpringToDatabase_JPA.SpringToDatabase_JPA.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> registerUser(@RequestBody CreateUserDto createUserDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(createUserDto));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginDto));
    }



}
