package com.user.redex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Nabeel Ahmed
 */
@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
@ComponentScan(basePackages = {"com.user.redex.*"})
public class RedexApplication {

	private Logger logger = LoggerFactory.getLogger(RedexApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RedexApplication.class, args);
	}
}
