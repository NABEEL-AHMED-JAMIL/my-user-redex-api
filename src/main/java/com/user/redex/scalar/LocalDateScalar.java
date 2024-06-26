package com.user.redex.scalar;

import graphql.language.StringValue;
import graphql.schema.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * @author Nabeel Ahmed
 */
public class LocalDateScalar implements Coercing<LocalDate, String> {

    private Logger logger = LoggerFactory.getLogger(LocalDateScalar.class);

    private static final String DATE_PATTERN = "dd-MMM-yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Serialize LocalDate to String
     * @param dataFetcherResult
     * @return String
     * */
    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalDate) {
            logger.info("LocalDateScalar for serialize the [LocalDate to String].");
            return ((LocalDate) dataFetcherResult).format(DATE_FORMATTER);
        }
        throw new CoercingSerializeException("Invalid LocalDate value: " + dataFetcherResult);
    }

    /**
     * Parse value from String to LocalDate
     * @param input
     * @return LocalDate
     * */
    @Override
    public LocalDate parseValue(Object input) throws CoercingParseValueException {
        if (input instanceof String) {
            try {
                logger.info("LocalDateScalar for parseValue the [String to LocalDate].");
                return LocalDate.parse((String) input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new CoercingParseValueException("Invalid LocalDate value: " + input);
            }
        }
        throw new CoercingParseValueException("Invalid LocalDate value: " + input);
    }

    /**
     * Parse value from StringValue to LocalDate
     * @param input
     * @return LocalDate
     * */
    @Override
    public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            try {
                logger.info("LocalDateScalar for parseLiteral the [StringValue to LocalDate].");
                return LocalDate.parse(((StringValue) input).getValue(), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                throw new CoercingParseLiteralException("Invalid LocalDate value: " + input);
            }
        }
        throw new CoercingParseLiteralException("Invalid LocalDate value: " + input);
    }
}