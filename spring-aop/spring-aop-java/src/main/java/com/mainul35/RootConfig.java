package com.mainul35;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = "com.mainul35.service")
@EnableAspectJAutoProxy
@Configuration
public class RootConfig {

    @Bean
    public EmployeeCRUDAspect loggingAspect() {
        return new EmployeeCRUDAspect();
    }
}
