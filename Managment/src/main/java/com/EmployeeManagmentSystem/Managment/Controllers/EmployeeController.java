package com.EmployeeManagmentSystem.Managment.Controllers;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/GetEmpList")
    public List<EmployeeDTO> getEmpList(){
        return employeeService.getAllEmployees();

    }

    @GetMapping("/Test")
    public String Test(){
        return "Контроллер работает!";
    }

}
