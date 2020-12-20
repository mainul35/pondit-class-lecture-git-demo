package com.example.controller;

import com.example.dto.DesignationDto;
import com.example.entity.Designation;
import com.example.service.DesignationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/designation")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @RequestMapping("/")
    public String getAllDesignation(Model model) {
        List<Designation> designationList = designationService.getAllDesignations();
        model.addAttribute("designationList", designationList);
        return "designation/designation";
    }

    @GetMapping("/add-designation")
    public String getAddDesignation(Model model) {
        model.addAttribute("designationDto", new DesignationDto());
        return "designation/add-designation";
    }

    @PostMapping("/save-designation")
    public String saveEmployee(@ModelAttribute DesignationDto designationDto) {
        designationService.save(designationDto);
        return "redirect:/designation/";
    }

    @GetMapping("/update/{id}")
    public String updateDesignation(@PathVariable("id") long id, Model model) {
        Designation designation = designationService.getById(id);
        if (designation == null) {
            throw new RuntimeException("User not Found By this id");
        }
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(designation, designationDto);
        model.addAttribute("designationDto", designationDto);

        return "designation/add-designation";
    }


    @GetMapping("/delete/{id}")
    public String deleteDesignation(@PathVariable("id") long id) {
        designationService.remove(id);
        return "redirect:/designation/";
    }
}
