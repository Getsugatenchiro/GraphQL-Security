package com.graphql_security.service;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.PageResponse;
import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Role;

public interface RoleService {

    PageResponse getAll(PageRequest pageRequest);

    Role getById(Long id);

    void delete(Long id);

    Role create(RoleCreateRequest roleCreateRequest);

    Role update(RoleUpdateRequest roleUpdateRequest);

}
