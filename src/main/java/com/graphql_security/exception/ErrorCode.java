package com.graphql_security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SCALAR_ERROR_1("Invalid ISO-8601 Instant format"),
    SCALAR_ERROR_2("Expected Instant type but was: "),
    SCALAR_ERROR_3("Expected String value but was: "),
    AUTH_SECURITY_CONTEXT_ERROR("Could not set user authentication in security context.");

    private final String description;

}
