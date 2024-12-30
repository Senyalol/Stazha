package com.EmployeeManagmentSystem.Managment.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "employee_department")
public class EmployeeDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_department_id_gen")
    @SequenceGenerator(name = "employee_department_id_gen", sequenceName = "employee_department_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "department_id")
    private Department department;

}