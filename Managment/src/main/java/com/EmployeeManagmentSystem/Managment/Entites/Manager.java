package com.EmployeeManagmentSystem.Managment.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_id_gen")
    @SequenceGenerator(name = "manager_id_gen", sequenceName = "manager_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer managerid;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

}