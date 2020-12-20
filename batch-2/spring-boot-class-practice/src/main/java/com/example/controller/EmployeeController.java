package com.example.controller;

import com.example.dto.DesignationDto;
import com.example.dto.EmployeeDto;
import com.example.entity.Designation;
import com.example.entity.Employee;
import com.example.service.DesignationService;
import com.example.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DesignationService designationService;

    public EmployeeController(EmployeeService employeeService, DesignationService designationService) {
        this.employeeService = employeeService;
        this.designationService = designationService;
    }

    @RequestMapping("/")
    public String getAllEmployee(Model model) {
        List<Employee> employeeList = employeeService.getAllEnabled();
        model.addAttribute("employeeList", employeeList);
        return "employee/index";
    }

    @GetMapping("/add-employee")
    public String getAddEmployee(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        List<Designation> designationList = designationService.getAllDesignations();
        model.addAttribute("designationListDto", this.generateDesignationDtoList(designationList));
        model.addAttribute("typeList", getEmploymentTypes());
        return "employee/add-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto) {
        employeeService.save(employeeDto);
        return "redirect:/employee/";
    }

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.getById(id);

        if (employeeOptional.isEmpty()) {
            model.addAttribute("error_message", "Employee with this ID was not found.");
            return "errors/404";
        }
        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = employeeOptional.get();
        BeanUtils.copyProperties(employee, employeeDto);
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(employee.getDesignation(), designationDto);
        employeeDto.setDesignationDto(designationDto);
        model.addAttribute("employeeDto", employeeDto);
        List<Designation> designationList = designationService.getAllDesignations();
        model.addAttribute("designationListDto", this.generateDesignationDtoList(designationList));
        model.addAttribute("typeList", getEmploymentTypes());
        return "employee/add-employee";
    }


    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeService.disable(id);
        return "redirect:/employee/";
    }

    private List<String> getEmploymentTypes() {
        List<String> typeList = new ArrayList<>();
        typeList.add("Part Time");
        typeList.add("Full Time");
        return typeList;
    }

    private List<DesignationDto> generateDesignationDtoList(List<Designation> designations) {
        List<DesignationDto> designationListDto = new ArrayList<>();
        for (Designation designation : designations) {
            DesignationDto designationDto = new DesignationDto();
            BeanUtils.copyProperties(designation, designationDto);
            designationListDto.add(designationDto);
        }
        return designationListDto;
    }
}
