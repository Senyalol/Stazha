package com.EmployeeManagmentSystem.Managment.Controllers;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}")
    public EmployeeDTO getEmpByName(@PathVariable String name){
        return employeeService.getEmployeeName(name);
    }

    @GetMapping("/FindEmpByStatus/{status}")
    public EmployeeDTO getEmpByStatus(@PathVariable String status){
        return employeeService.getEmployeeByStatus(status);
    }

    @GetMapping("/FindEmpByManager/{name}")
    public List<EmployeeDTO> getEmpByManager(@PathVariable String name){
        return employeeService.getEmployeeByManagerName(name);
    }

    @GetMapping("/FindEmpByDepartmentName/{name}")
    public List<EmployeeDTO> getEmpByDepartmentName(@PathVariable String name){
        return employeeService.getEmployeeByDepartmentName(name);
    }

    @PostMapping("/AddEmployee")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.CreateEmployee(employeeDTO);
    }

    @DeleteMapping("/RemoveEmp/{name}")
    public void removeEmployeeByName(@PathVariable String name){
        employeeService.RemoveEmployee(name);
    }

    @PatchMapping("/ChangeParamsEmp/{name}")
    public void changeEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable String name){
        employeeService.ChangeParamsEmployee(name, employeeDTO);
    }

    @GetMapping("/Test")
    public String Test(){
        return "Контроллер работает!";
    }

}
