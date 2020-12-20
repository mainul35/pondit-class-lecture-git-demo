package com.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Designation")
@Data
@NoArgsConstructor
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long designationId;

    @Column
    private String designationName;
}
