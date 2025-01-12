package com.EmployeeManagmentSystem.Managment.Repository;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeDepartmentDTO;
import com.EmployeeManagmentSystem.Managment.Entites.EmployeeDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentAndEmpRepository extends JpaRepository<EmployeeDepartment, Integer> {
    List<EmployeeDepartment> findByEmployeeId(int employeeId);
    List<EmployeeDepartment> findByDepartmentId(int departmentId);
}
