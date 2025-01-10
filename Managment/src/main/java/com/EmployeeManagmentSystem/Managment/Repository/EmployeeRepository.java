package com.EmployeeManagmentSystem.Managment.Repository;

import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Employee findById(int id);
    Employee findByName(String name);
    Employee findByStatus(String status);
    List<Employee> findByManagerId(int managerId);
}
