package com.spring5.practice;

import com.spring5.practice.config.security.AuthSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
		"com.spring5.practice.service",
		"com.spring5.practice.config.persistence",
		"com.spring5.practice.config.security",
})
//@ComponentScan(basePackageClasses = {StudentService.class})
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	public AuthSuccessHandler authSuccessHandler() {
		return new AuthSuccessHandler();
	}
}
