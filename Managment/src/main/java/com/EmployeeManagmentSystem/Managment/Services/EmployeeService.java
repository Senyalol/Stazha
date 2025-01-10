package com.EmployeeManagmentSystem.Managment.Services;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import com.EmployeeManagmentSystem.Managment.Entites.Manager;
import com.EmployeeManagmentSystem.Managment.Mappers.EmployeeMapping;
import com.EmployeeManagmentSystem.Managment.Repository.EmployeeRepository;
import com.EmployeeManagmentSystem.Managment.Repository.ManagerRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@JsonSerialize
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapping employeeMapper;
    private final ManagerRepository managerRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapping employeeMapper, ManagerRepository managerRepository)
    {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.managerRepository = managerRepository;
    }

    //Method that returns all employees
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> listEmployees = employeeRepository.findAll();
        return employeeMapper.toEmployeeDtos(listEmployees);
    }

    //Method that returns employee by name
    public EmployeeDTO getEmployeeName(String name) {
        Employee employee = employeeRepository.findByName(name);
        return employeeMapper.toEmployeeDTO(employee);
    }

    //Method that return employee by status
    public EmployeeDTO getEmployeeByStatus(String status) {
        return employeeMapper.toEmployeeDTO(employeeRepository.findByStatus(status));
    }

    //Method that return employee by name manager
    public List<EmployeeDTO> getEmployeeByManagerName(String name) {
        Manager manager = managerRepository.findByName(name);
        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());
        return employeeMapper.toEmployeeDtos(employees);
    }

    //Method for create employee
    public Employee CreateEmployee(EmployeeDTO createEmployeeDTO){
        Employee employee = employeeMapper.toEmployee(createEmployeeDTO);
        employeeRepository.save(employee);
        return employee;
    }

    //Method for remove employee
    public void RemoveEmployee(String name) {
        employeeRepository.deleteById(employeeRepository.findByName(name).getId());
    }

    //Method for change params employee
    public void ChangeParamsEmployee(String name , EmployeeDTO ChangeEmployeeDTO) {
        Employee employeeToUpdate = employeeRepository.findByName(name);

        if(ChangeEmployeeDTO.getName() != null){
            employeeToUpdate.setName(ChangeEmployeeDTO.getName());
        }
        if(ChangeEmployeeDTO.getStatus() != null){
            employeeToUpdate.setStatus(ChangeEmployeeDTO.getStatus());
        }
        if(ChangeEmployeeDTO.getPhotoUrl() != null){
            employeeToUpdate.setPhotoUrl(ChangeEmployeeDTO.getPhotoUrl());
        }
        if(ChangeEmployeeDTO.getEmail() != null){
            employeeToUpdate.setEmail(ChangeEmployeeDTO.getEmail());
        }
        if(ChangeEmployeeDTO.getManagerid() != null){
            Manager manager = managerRepository.findById(ChangeEmployeeDTO.getManagerid())
                    .orElse(null);
            employeeToUpdate.setManager(manager);
        }

        employeeRepository.save(employeeToUpdate);
    }

}
