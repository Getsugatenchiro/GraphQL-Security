package com.graphql_security.dto;

import java.util.Set;

public record RoleUpdateRequest(Long id,
                                String name,
                                Set<PermissionRequest> permissions) {
}
