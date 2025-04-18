package com.graphql_security.mapper;

import com.graphql_security.dto.PermissionRequest;
import com.graphql_security.dto.RoleCreateRequest;
import com.graphql_security.dto.RoleUpdateRequest;
import com.graphql_security.entities.Permission;
import com.graphql_security.entities.Role;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-08T15:19:33+0300",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleCreateRequest roleCreateRequest) {
        if ( roleCreateRequest == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( roleCreateRequest.name() );
        role.setPermissions( permissionRequestSetToPermissionSet( roleCreateRequest.permissions() ) );

        linkPermissions( role );

        return role;
    }

    @Override
    public Role partialUpdate(RoleUpdateRequest roleUpdateRequest, Role role) {
        if ( roleUpdateRequest == null ) {
            return role;
        }

        if ( roleUpdateRequest.id() != null ) {
            role.setId( roleUpdateRequest.id() );
        }
        if ( roleUpdateRequest.name() != null ) {
            role.setName( roleUpdateRequest.name() );
        }
        if ( role.getPermissions() != null ) {
            Set<Permission> set = permissionRequestSetToPermissionSet( roleUpdateRequest.permissions() );
            if ( set != null ) {
                role.getPermissions().clear();
                role.getPermissions().addAll( set );
            }
        }
        else {
            Set<Permission> set = permissionRequestSetToPermissionSet( roleUpdateRequest.permissions() );
            if ( set != null ) {
                role.setPermissions( set );
            }
        }

        linkPermissions( role );

        return role;
    }

    protected Permission permissionRequestToPermission(PermissionRequest permissionRequest) {
        if ( permissionRequest == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setName( permissionRequest.name() );

        return permission;
    }

    protected Set<Permission> permissionRequestSetToPermissionSet(Set<PermissionRequest> set) {
        if ( set == null ) {
            return null;
        }

        Set<Permission> set1 = new LinkedHashSet<Permission>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionRequest permissionRequest : set ) {
            set1.add( permissionRequestToPermission( permissionRequest ) );
        }

        return set1;
    }
}
