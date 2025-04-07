package com.graphql_security.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler({
            DataIntegrityViolationException.class,
            MethodArgumentNotValidException.class,
            AccessDeniedException.class,
            NoSuchElementException.class,
            Exception.class,
            CustomException.class
    })
    public GraphQLError handleAll(Exception exception, DataFetchingEnvironment env) {
        return buildGraphQLError(exception, env);
    }

    private GraphQLError buildGraphQLError(Exception exception, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .message(exception.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .extensions(Map.of("classification", exception.getClass().getSimpleName()))
                .build();
    }

}