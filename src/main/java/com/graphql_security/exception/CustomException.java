package com.graphql_security.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDescription());
    }

    public CustomException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription());
        initCause(cause);
    }

}
