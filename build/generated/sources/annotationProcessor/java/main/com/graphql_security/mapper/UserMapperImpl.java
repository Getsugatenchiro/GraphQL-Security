package com.graphql_security.mapper;

import com.graphql_security.dto.RegisterRequest;
import com.graphql_security.dto.UserUpdateRequest;
import com.graphql_security.entities.User;
import com.graphql_security.service.RoleService;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T15:19:33+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoderMapper passwordEncoderMapper;

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User user = new User();

        user.setRole( roleService.getById( registerRequest.roleId() ) );
        user.setPassword( passwordEncoderMapper.encode( registerRequest.password() ) );
        user.setUsername( registerRequest.username() );

        return user;
    }

    @Override
    public User partialUpdate(UserUpdateRequest userUpdateRequest, User user) {
        if ( userUpdateRequest == null ) {
            return user;
        }

        if ( userUpdateRequest.roleId() != null ) {
            user.setRole( roleService.getById( userUpdateRequest.roleId() ) );
        }
        if ( userUpdateRequest.id() != null ) {
            user.setId( userUpdateRequest.id() );
        }
        if ( userUpdateRequest.username() != null ) {
            user.setUsername( userUpdateRequest.username() );
        }

        return user;
    }
}
