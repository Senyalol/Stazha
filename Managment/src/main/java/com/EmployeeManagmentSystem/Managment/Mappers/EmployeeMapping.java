package com.EmployeeManagmentSystem.Managment.Mappers;

import com.EmployeeManagmentSystem.Managment.DTO.EmployeeDTO;
import com.EmployeeManagmentSystem.Managment.Entites.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapping {

    EmployeeDTO toDTO(Employee employee);

    Employee toEntity(EmployeeDTO employeeDTO);

    List<EmployeeDTO> toDTOs(List<Employee> employees);


}
