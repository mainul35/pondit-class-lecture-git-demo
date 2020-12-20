package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //Custom made By Query Language
    @Query(value = "SELECT e From Employee e where e.enable=true")
    List<Employee> findAllEnable();

    //Custom made But Spring Suffix Provide
    List<Employee> findAllByEnableTrue();

}
