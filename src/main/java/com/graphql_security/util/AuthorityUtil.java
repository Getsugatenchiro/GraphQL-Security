package com.graphql_security.util;

import com.graphql_security.entities.User;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class AuthorityUtil {

    public Set<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

        user.getRole().getPermissions().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.getName()))
        );

        return authorities;
    }
}
