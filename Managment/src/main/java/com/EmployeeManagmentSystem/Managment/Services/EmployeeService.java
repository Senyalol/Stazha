package com.EmployeeManagmentSystem.Managment.Services;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import com.EmployeeManagmentSystem.Managment.Mappers.EmployeeMapping;
import com.EmployeeManagmentSystem.Managment.Repository.EmployeeRepository;
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

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapping employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> listEmployees = employeeRepository.findAll();
        return employeeMapper.toDTOs(listEmployees);
    }

//    public Employee CreateEmployee(EmployeeDTO createEmployeeDTO){
//        Employee employee = EmployeeMapper.INSTANCE.toEntity(createEmployeeDTO);
//        return employeeRepository.save(employee);
//
//    }

}
