package com.graphql_security.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    private static final GraphQLScalarType INSTANT_SCALAR = GraphQLScalarType.newScalar()
            .name("Instant")
            .description("Instant type")
            .coercing(new InstantScalar())
            .build();

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return builder -> builder
                .scalar(INSTANT_SCALAR)
                .scalar(ExtendedScalars.GraphQLLong);
    }

}