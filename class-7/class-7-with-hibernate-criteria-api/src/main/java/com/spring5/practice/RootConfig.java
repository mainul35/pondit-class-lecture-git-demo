package com.spring5.practice;

import com.spring5.practice.config.HibernateConfig;
import com.spring5.practice.exceptions.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.spring5.practice.service"})
public class RootConfig {
//	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Bean
	public HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
}
