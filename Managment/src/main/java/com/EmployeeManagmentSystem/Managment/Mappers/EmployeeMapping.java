package com.EmployeeManagmentSystem.Managment.Mappers;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import com.EmployeeManagmentSystem.Managment.Entites.Manager;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapping {

    default EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId( employee.getId() );
        employeeDTO.setName( employee.getName() );
        employeeDTO.setStatus( employee.getStatus());
        employeeDTO.setPhotoUrl( employee.getPhotoUrl() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setManagerid(employee.getManager().getId());
        return employeeDTO;
    }

    default Employee toEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId( employeeDTO.getId() );
        employee.setName( employeeDTO.getName() );
        employee.setStatus( employeeDTO.getStatus() );
        employee.setPhotoUrl( employeeDTO.getPhotoUrl() );
        employee.setEmail( employeeDTO.getEmail() );

        if (employeeDTO.getManagerid() != null) {
            Manager manager = new Manager();
            manager.setId(employeeDTO.getManagerid());
            employee.setManager(manager);
        } else {
            employee.setManager(null); // или другое значение по умолчанию
        }

        return employee;
    }

    default List<EmployeeDTO> toEmployeeDtos(List<Employee> employees) {
        if (employees == null) {
            return null;
        }

        return employees.stream()
                .map(this::toEmployeeDTO) // Используем существующий метод
                .collect(Collectors.toList());
    }

}
