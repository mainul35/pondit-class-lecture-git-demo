package com.example.service;

import com.example.dto.DesignationDto;
import com.example.entity.Designation;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.DesignationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {

    private final DesignationRepository designationRepository;

    public DesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public void save(DesignationDto designationDto) {
        Designation designation = new Designation();
        BeanUtils.copyProperties(designationDto, designation);
        designationRepository.save(designation);
    }

    public Designation getById(long id) {
        return designationRepository.getOne(id);
    }

    public void remove(long id) {
        Optional<Designation> designation = designationRepository.findById(id);
        if (!designation.isEmpty()) {
            designationRepository.delete(designation.get());
        } else {
            throw new ResourceNotFoundException("Designation not found with this id: "+id);
        }
    }
}
