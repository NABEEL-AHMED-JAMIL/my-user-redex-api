package com.user.redex.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class RedexConfiguration {

    private Logger logger = LoggerFactory.getLogger(RedexConfiguration.class);

}
