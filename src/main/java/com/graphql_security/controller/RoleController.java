package com.graphql_security.controller;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.PageResponse;
import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Role;
import com.graphql_security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('ROLE.ALL')")
    public PageResponse getAllRoles(@Argument PageRequest pageRequest) {
        return roleService.getAll(pageRequest);
    }

    @QueryMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('ROLE.GET')")
    public Role getByRole(@Argument Long id) {
        return roleService.getById(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('ROLE.DELETE')")
    public Boolean deleteRole(@Argument Long id) {
        roleService.delete(id);
        return true;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('ROLE.CREATE')")
    public Role createRole(@Argument RoleCreateRequest roleCreateRequest) {
        return roleService.create(roleCreateRequest);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasAnyAuthority('ROLE.UPDATE')")
    public Role updateRole(@Argument RoleUpdateRequest roleUpdateRequest) {
        return roleService.update(roleUpdateRequest);
    }

}
