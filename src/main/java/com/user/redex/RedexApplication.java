package com.user.redex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Nabeel Ahmed
 */
@SpringBootApplication
@ComponentScan(value = "com.user.redex.*")
public class RedexApplication {

	private Logger logger = LoggerFactory.getLogger(RedexApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RedexApplication.class, args);
	}

}
