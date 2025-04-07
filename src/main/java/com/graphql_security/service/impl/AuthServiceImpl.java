package com.graphql_security.service.impl;

import com.graphql_security.dto.LoginRequest;
import com.graphql_security.dto.LoginResponse;
import com.graphql_security.dto.RegisterRequest;
import com.graphql_security.entities.User;
import com.graphql_security.mapper.UserMapper;
import com.graphql_security.repository.UserRepository;
import com.graphql_security.service.AuthService;
import com.graphql_security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil tokenProvider;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new LoginResponse(tokenProvider.generateToken(userDetails));
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        return userRepository.save(userMapper.toEntity(registerRequest));
    }
}