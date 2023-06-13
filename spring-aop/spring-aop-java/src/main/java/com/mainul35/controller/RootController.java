package com.mainul35.controller;

import com.mainul35.Audit;
import com.mainul35.EmployeeDTO;
import com.mainul35.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Autowired
    EmployeeManager employeeManager;

    @GetMapping("/")
    public String getSomeString() {
        System.out.println("printing....");

        employeeManager.getEmployeeById(1);
        employeeManager.createEmployee(new EmployeeDTO());
        return "asdfgjk";
    }

}
