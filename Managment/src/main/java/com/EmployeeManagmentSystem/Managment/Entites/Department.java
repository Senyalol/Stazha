package com.EmployeeManagmentSystem.Managment.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen", sequenceName = "department_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}