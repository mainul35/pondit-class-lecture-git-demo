package com.example.init;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializeData implements CommandLineRunner {

    @Autowired
    EmployeeRepository repository;

    @Override
    public void run(String... args) throws Exception {

    }
}
