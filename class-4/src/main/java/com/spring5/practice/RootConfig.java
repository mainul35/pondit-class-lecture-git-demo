package com.spring5.practice;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.spring5.practice.service"})
//@ComponentScan(basePackageClasses = {StudentService.class})
public class RootConfig {
}
