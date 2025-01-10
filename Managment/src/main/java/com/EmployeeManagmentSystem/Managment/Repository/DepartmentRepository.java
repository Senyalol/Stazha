package com.EmployeeManagmentSystem.Managment.Repository;

import com.EmployeeManagmentSystem.Managment.Entites.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByName(String name);
}
