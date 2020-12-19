package com.springboot.practice;

import com.springboot.practice.init.InitializeData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(new Class[]{HelloWorldApplication.class}, args);
    }
}
