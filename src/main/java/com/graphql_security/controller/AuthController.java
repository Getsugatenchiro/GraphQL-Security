package com.graphql_security.controller;

import com.graphql_security.dto.LoginRequest;
import com.graphql_security.dto.LoginResponse;
import com.graphql_security.dto.RegisterRequest;
import com.graphql_security.entities.User;
import com.graphql_security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @MutationMapping
    public LoginResponse login(@Argument LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @MutationMapping
    public User register(@Argument RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

}
