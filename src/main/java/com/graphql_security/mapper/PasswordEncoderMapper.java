package com.graphql_security.mapper;

import com.graphql_security.annotation.EncodedMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncoderMapper {

    protected final PasswordEncoder passwordEncoder;

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }

}
