package com.EmployeeManagmentSystem.Managment.Controllers;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeDepartmentDTO;
import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public EmployeeDTO getEmpByName(@PathVariable String name){
        return employeeService.getEmployeeName(name);
    }

    @GetMapping("/FindEmpByStatus/{status}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public EmployeeDTO getEmpByStatus(@PathVariable String status){
        return employeeService.getEmployeeByStatus(status);
    }

    @GetMapping("/FindEmpByManager/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public List<EmployeeDTO> getEmpByManager(@PathVariable String name){
        return employeeService.getEmployeeByManagerName(name);
    }

    @GetMapping("/FindEmpByDepartmentName/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public List<EmployeeDTO> getEmpByDepartmentName(@PathVariable String name){
        return employeeService.getEmployeeByDepartmentName(name);
    }

    @GetMapping("/ViewDepartments/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public List<EmployeDepartmentDTO> getAllDepartments(@PathVariable int id){
            return employeeService.getEmployeeDepartmentByEmployeeId(id);
    }

    @PostMapping("/AddEmployee")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.CreateEmployee(employeeDTO);
    }

    @PostMapping("/AddEmpToDepart")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void addEmpToDepartment(@RequestBody EmployeDepartmentDTO empDepartmentDTO){
        employeeService.AddEmployeeToDepartment(empDepartmentDTO);
    }

    @DeleteMapping("/RemoveEmpFromDepart/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void removeEmpFromDepartment(@PathVariable int id){
        employeeService.RemoveEmployeeFromDepartment(id);
    }

    @DeleteMapping("/RemoveEmp/{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void removeEmployeeByName(@PathVariable String name){
        employeeService.RemoveEmployee(name);
    }

    @PatchMapping("/ChangeParamsEmp/{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void changeEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable String name){
        employeeService.ChangeParamsEmployee(name, employeeDTO);
    }

    @PatchMapping("ChangeLinks/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void changelinks(@RequestBody EmployeDepartmentDTO employeDepartmentDTO, @PathVariable int id){
        employeeService.ChangeLinkEmpDepart(id,employeDepartmentDTO);
    }


//    @GetMapping("/Test")
//    public String Test(){
//        return "Контроллер работает!";
//    }

}
