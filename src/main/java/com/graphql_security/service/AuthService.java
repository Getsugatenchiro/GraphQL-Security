package com.graphql_security.service;

import com.graphql_security.dto.LoginRequest;
import com.graphql_security.dto.LoginResponse;
import com.graphql_security.dto.RegisterRequest;
import com.graphql_security.entities.User;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    User register(RegisterRequest registerRequest);

}
