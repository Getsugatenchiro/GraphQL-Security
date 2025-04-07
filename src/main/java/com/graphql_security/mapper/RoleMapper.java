package com.graphql_security.mapper;

import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    Role toEntity(RoleCreateRequest roleCreateRequest);

    @AfterMapping
    default void linkPermissions(@MappingTarget Role role) {
        role.getPermissions().forEach(permission -> permission.setRole(role));
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleUpdateRequest roleUpdateRequest, @MappingTarget Role role);

}