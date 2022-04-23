package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class,
		UserDetailsServiceAutoConfiguration.class,
		LiquibaseAutoConfiguration.class})
public class SpringBootInitializer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootInitializer.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootInitializer.class);
	}
}
