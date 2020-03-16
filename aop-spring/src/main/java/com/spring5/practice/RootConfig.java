package com.spring5.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan(basePackages = {
		"com.spring5.practice.service",
		"com.spring5.practice.config.persistence",
		"com.spring5.practice.config.security",
		"com.spring5.practice.config.aop",
})
//@ComponentScan(basePackageClasses = {StudentService.class})
@EnableAspectJAutoProxy
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
