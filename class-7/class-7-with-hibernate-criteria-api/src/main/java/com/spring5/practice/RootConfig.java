package com.spring5.practice;

import com.spring5.practice.config.HibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.spring5.practice.service"})
//@ComponentScan(basePackageClasses = {StudentService.class})
public class RootConfig {
	@Bean
	GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
}
