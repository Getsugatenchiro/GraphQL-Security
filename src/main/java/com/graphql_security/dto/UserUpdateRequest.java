package com.graphql_security.dto;

public record UserUpdateRequest(Long id,
                                String username,
                                Long roleId) {
}
