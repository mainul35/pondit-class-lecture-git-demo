package com.example.dto;

import com.example.entity.Designation;
import lombok.Data;

@Data
public class EmployeeDto {
    private long id;
    private String name;
    private int age;
    private String address;
    private double salary;
    private DesignationDto designationDto;
    private String workType;
}
