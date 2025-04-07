package com.graphql_security.service;

import com.graphql_security.dto.PageRequest;
import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Role;
import org.springframework.data.domain.Page;

public interface RoleService {

    Page<Role> getAll(PageRequest pageRequest);

    Role getById(Long id);

    void delete(Long id);

    Role create(RoleCreateRequest roleCreateRequest);

    Role update(RoleUpdateRequest roleUpdateRequest);

}
