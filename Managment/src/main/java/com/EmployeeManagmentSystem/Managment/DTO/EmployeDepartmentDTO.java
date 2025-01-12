package com.EmployeeManagmentSystem.Managment.DTO;

import lombok.Data;

@Data
public class EmployeDepartmentDTO {

    private int id;
    private Integer EmployeeId;
    private Integer DepartmentId;

    public EmployeDepartmentDTO(int id, Integer EmployeeId, Integer DepartmentId) {
        this.id = id;
        this.EmployeeId = EmployeeId;
        this.DepartmentId = DepartmentId;
    }

}
