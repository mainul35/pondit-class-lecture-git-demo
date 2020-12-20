package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Employee")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String address;

    @Column
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designationId")
    private Designation designation;

    @Column
    private String workType;

    @Column
    private Boolean enable = true;
}
