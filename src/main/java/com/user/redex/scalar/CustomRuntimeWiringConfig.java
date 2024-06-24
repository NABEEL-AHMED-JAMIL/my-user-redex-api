package com.user.redex.scalar;

import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author Nabeel Ahmed
 */
@Component
public class CustomRuntimeWiringConfig implements RuntimeWiringConfigurer {

    private Logger logger = LoggerFactory.getLogger(CustomRuntimeWiringConfig.class);

    public CustomRuntimeWiringConfig() {}

    /**
     * Method use to bind all scalar runtime
     * @param builder
     * */
    @Override
    public void configure(RuntimeWiring.Builder builder) {
        builder
            .scalar(localDateType())
            .scalar(localDateTimeType())
            .build();
    }

    /**
     * Scalar type for local date
     * @return GraphQLScalarType[LocalDate]
     * */
    public GraphQLScalarType localDateType() {
        return GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("LocalDate Type")
            .coercing(new LocalDateScalar())
            .build();
    }

    /**
     * Scalar type for local date time
     * @return GraphQLScalarType[LocalDateTimeScalar]
     * */
    public GraphQLScalarType localDateTimeType() {
        return GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("LocalDateTime Type")
            .coercing(new LocalDateTimeScalar())
            .build();
    }
}
