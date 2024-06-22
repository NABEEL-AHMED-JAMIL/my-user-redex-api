package com.user.redex.scalar;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Nabeel Ahmed
 */
public class LocalDateTimeScalar implements Coercing<LocalDateTime, String> {

    private Logger logger = LoggerFactory.getLogger(LocalDateTimeScalar.class);

    private static final String DATE_PATTERN = "dd-MMM-yyyy HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalDateTime) {
            logger.info("LocalDateScalar for serialize the [LocalDateTime to String].");
            return ((LocalDateTime) dataFetcherResult).format(DATE_FORMATTER);
        }
        throw new CoercingSerializeException("Invalid LocalDateTime value: " + dataFetcherResult);
    }

    @Override
    public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
        if (input instanceof String) {
            try {
                logger.info("LocalDateScalar for parseValue the [String to LocalDateTime].");
                return LocalDateTime.parse((String) input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new CoercingParseValueException("Invalid LocalDate value: " + input);
            }
        }
        throw new CoercingParseValueException("Invalid LocalDate value: " + input);
    }

    @Override
    public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            try {
                logger.info("LocalDateScalar for parseLiteral the [StringValue to LocalDateTime].");
                return LocalDateTime.parse(((StringValue) input).getValue(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new CoercingParseLiteralException("Invalid LocalDate value: " + input);
            }
        }
        throw new CoercingParseLiteralException("Invalid LocalDate value: " + input);
    }
}
