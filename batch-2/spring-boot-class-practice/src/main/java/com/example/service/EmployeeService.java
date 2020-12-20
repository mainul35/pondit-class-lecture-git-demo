package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.entity.Designation;
import com.example.entity.Employee;
import com.example.repository.DesignationRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DesignationRepository designationRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DesignationRepository designationRepository) {
        this.employeeRepository = employeeRepository;
        this.designationRepository = designationRepository;
    }

    public List<Employee> getAllEnabled() {
        return employeeRepository.findAllByEnableTrue();
    }

    public void save(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeDto.getId());
        Employee employee = optionalEmployee.orElseGet(Employee::new);
        BeanUtils.copyProperties(employeeDto, employee);
        Designation designation = new Designation();
        BeanUtils.copyProperties(employeeDto.getDesignationDto(), designation);
//        employee.setDesignation(designationRepository.getOne(employeeDto.getDesignationId()));
        employee.setDesignation(designation);
        employeeRepository.save(employee);
    }

    public void disable(Long id) {
        Employee employee=employeeRepository.getOne(id);
        employee.setEnable(false);
        employeeRepository.save(employee);
    }

    public Optional<Employee> getById(long id) {
        return employeeRepository.findById(id);
    }
}
