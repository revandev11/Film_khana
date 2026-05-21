package org.my.project.controller;

import jakarta.validation.Valid;
import org.my.project.dto.request.LoginRequest;
import org.my.project.dto.request.RegisterRequest;
import org.my.project.dto.response.AuthResponse;
import org.my.project.entity.User;
import org.my.project.security.JwtTokenProvider;
import org.my.project.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse authResponse = authService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authResponse);
    }
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }









}

