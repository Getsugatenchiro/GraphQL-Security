package com.graphql_security.config;

import com.graphql_security.exception.CustomException;
import com.graphql_security.exception.ErrorCode;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class InstantScalar implements Coercing<Instant, String> {

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (Objects.isNull(dataFetcherResult))
            return null;

        if (dataFetcherResult instanceof Instant instant)
            return instant.toString();

        throw new CustomException(ErrorCode.SCALAR_ERROR_2.getDescription() + dataFetcherResult.getClass().getSimpleName());
    }

    @Override
    public Instant parseValue(Object input) throws CoercingParseValueException {
        if (Objects.isNull(input))
            return null;

        try {
            return Instant.parse(input.toString());
        } catch (DateTimeParseException e) {
            throw new CustomException(ErrorCode.SCALAR_ERROR_1, e);
        }
    }

    @Override
    public Instant parseLiteral(Object input) throws CoercingParseLiteralException {
        if (Objects.isNull(input))
            return null;

        if (input instanceof StringValue stringValue) {
            try {
                return Instant.parse(stringValue.getValue());
            } catch (DateTimeParseException e) {
                throw new CustomException(ErrorCode.SCALAR_ERROR_1);
            }
        }
        throw new CustomException(ErrorCode.SCALAR_ERROR_3.getDescription() + input.getClass().getSimpleName());
    }

} 