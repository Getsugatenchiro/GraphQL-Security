package com.graphql_security.mapper;

import com.graphql_security.annotation.EncodedMapping;
import com.graphql_security.dto.RegisterRequest;
import com.graphql_security.dto.UserUpdateRequest;
import com.graphql_security.entities.User;
import com.graphql_security.service.RoleService;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoleService.class, PasswordEncoderMapper.class})
public interface UserMapper {

    @Mapping(target = "role", source = "roleId")
    @Mapping(target = "password", source = "password", qualifiedBy = EncodedMapping.class)
    User toEntity(RegisterRequest registerRequest);

    @Mapping(target = "role", source = "roleId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserUpdateRequest userUpdateRequest, @MappingTarget User user);

}