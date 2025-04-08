package com.graphql_security.controller;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.PageResponse;
import com.graphql_security.dto.UserUpdateRequest;
import com.graphql_security.entities.User;
import com.graphql_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('USER.ALL')")
    public PageResponse getAllUsers(@Argument PageRequest pageRequest) {
        return userService.getAll(pageRequest);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('USER.GET')")
    public User getByUser(@Argument Long id) {
        return userService.getById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('USER.DELETE')")
    public Boolean deleteUser(@Argument Long id) {
        userService.delete(id);
        return true;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('USER.UPDATE')")
    public User updateUser(@Argument UserUpdateRequest userUpdateRequest) {
        return userService.update(userUpdateRequest);
    }

} 