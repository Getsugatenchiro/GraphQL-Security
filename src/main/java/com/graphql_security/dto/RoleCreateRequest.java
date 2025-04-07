package com.graphql_security.dto;

import java.util.Set;

public record RoleCreateRequest(String name,
                                Set<PermissionRequest> permissions) {
}
