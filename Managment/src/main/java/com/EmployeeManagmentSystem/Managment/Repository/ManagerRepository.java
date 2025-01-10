package com.EmployeeManagmentSystem.Managment.Repository;

import com.EmployeeManagmentSystem.Managment.Entites.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    Manager findById(int id);
    Manager findByName(String name);
}
