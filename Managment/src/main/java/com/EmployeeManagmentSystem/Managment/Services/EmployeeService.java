package com.EmployeeManagmentSystem.Managment.Services;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeDepartmentDTO;
import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Entites.Department;
import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import com.EmployeeManagmentSystem.Managment.Entites.EmployeeDepartment;
import com.EmployeeManagmentSystem.Managment.Entites.Manager;
import com.EmployeeManagmentSystem.Managment.Mappers.EmployeeMapping;
import com.EmployeeManagmentSystem.Managment.Repository.DepartmentAndEmpRepository;
import com.EmployeeManagmentSystem.Managment.Repository.DepartmentRepository;
import com.EmployeeManagmentSystem.Managment.Repository.EmployeeRepository;
import com.EmployeeManagmentSystem.Managment.Repository.ManagerRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@JsonSerialize
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapping employeeMapper;
    private final ManagerRepository managerRepository;
    private final DepartmentAndEmpRepository departmentAndEmpRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeMapping employeeMapper,
                           ManagerRepository managerRepository,
                           DepartmentAndEmpRepository departmentAndEmpRepository,
                           DepartmentRepository departmentRepository)
    {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.managerRepository = managerRepository;
        this.departmentAndEmpRepository = departmentAndEmpRepository;
        this.departmentRepository = departmentRepository;
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

    //Method that return employees by name manager
    public List<EmployeeDTO> getEmployeeByManagerName(String name) {
        Manager manager = managerRepository.findByName(name);
        List<Employee> employees = employeeRepository.findByManagerId(manager.getId());
        return employeeMapper.toEmployeeDtos(employees);
    }

    //Method that return employees by department name
    public List<EmployeeDTO> getEmployeeByDepartmentName(String departmentName) {
        Department department = departmentRepository.findByName(departmentName);
        List<EmployeeDepartment> ED = departmentAndEmpRepository.findByDepartmentId(department.getId());

        List<Employee> employees = ED.stream().map(EmployeeDepartment::getEmployee).collect(Collectors.toList());
        return employeeMapper.toEmployeeDtos(employees);
    }

    //Method that return links employee - department
    public List<EmployeDepartmentDTO> getEmployeeDepartmentByEmployeeId(int employeeId) {
        List<EmployeeDepartment> AllDepartmentsEmployee = departmentAndEmpRepository.findByEmployeeId(employeeId);

        List<EmployeDepartmentDTO> employeeDepartmentDTOList = new ArrayList<>();

        for (EmployeeDepartment employeeDepartment : AllDepartmentsEmployee) {
            EmployeDepartmentDTO dto = new EmployeDepartmentDTO(
                    employeeDepartment.getId(),    // или другой идентификатор
                    employeeDepartment.getEmployee().getId(),  // предположим, что у вас есть это поле
                    employeeDepartment.getDepartment().getId()  // предположим, что у вас есть это поле
            );
            employeeDepartmentDTOList.add(dto);
        }

        return employeeDepartmentDTOList;
    }

    //Method that add employee to department
    public EmployeeDepartment AddEmployeeToDepartment(EmployeDepartmentDTO employeeDepartmentDTO) {

        Employee employee = employeeRepository.findById(employeeDepartmentDTO.getEmployeeId()).get();
        Department department = departmentRepository.findById(employeeDepartmentDTO.getDepartmentId()).get();

        EmployeeDepartment employeeDepartment = new EmployeeDepartment();

        employeeDepartment.setEmployee(employee);
        employeeDepartment.setDepartment(department);

        departmentAndEmpRepository.save(employeeDepartment);
        return employeeDepartment;
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

    //Method that remove employee from department
    public void RemoveEmployeeFromDepartment(int id){
        departmentAndEmpRepository.deleteById(id);
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

    //Method that change links between employees and departments
    public void ChangeLinkEmpDepart(int id, EmployeDepartmentDTO ChangeEmployeeDTO) {
        EmployeeDepartment employeeDepartment = departmentAndEmpRepository.findById(id).get();

        if(ChangeEmployeeDTO.getEmployeeId() != null){
            employeeDepartment.setEmployee(employeeRepository.findById(ChangeEmployeeDTO.getEmployeeId()).get());
        }
        if(ChangeEmployeeDTO.getDepartmentId() != null){
            employeeDepartment.setDepartment(departmentRepository.findById(ChangeEmployeeDTO.getDepartmentId()).get());
        }

        departmentAndEmpRepository.save(employeeDepartment);
    }

}
