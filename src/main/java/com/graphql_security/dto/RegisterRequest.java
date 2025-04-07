package com.graphql_security.dto;

public record RegisterRequest(String username,
                              String password,
                              Long roleId) {
}
