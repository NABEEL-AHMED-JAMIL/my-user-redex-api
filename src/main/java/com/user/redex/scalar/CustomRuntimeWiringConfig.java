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

    @Override
    public void configure(RuntimeWiring.Builder builder) {
        builder
            .scalar(localDateType())
            .scalar(localDateTimeType())
            .build();
    }

    public GraphQLScalarType localDateType() {
        return GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("LocalDate Type")
            .coercing(new LocalDateScalar())
            .build();
    }

    public GraphQLScalarType localDateTimeType() {
        return GraphQLScalarType.newScalar()
            .name("LocalDateTime")
            .description("LocalDateTime Type")
            .coercing(new LocalDateTimeScalar())
            .build();
    }
}
