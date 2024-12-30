package com.EmployeeManagmentSystem.Managment.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("photo_url")
    private String photoUrl;

    @JsonProperty("email")
    private String email;

    @JsonProperty("manager_id")
    private Integer managerid;

}

//package com.EmployeeManagmentSystem.Managment.DTO;
//
//public class EmployeeDTO {
//    private Integer id;
//    private String name;
//    private String status;
//    private String photo_url;
//    private String email;
//    private Integer manager;
//
//    // Конструктор по умолчанию
//    public EmployeeDTO() {}
//
//    // Геттеры и сеттеры
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getPhoto_url() {
//        return photo_url;
//    }
//
//    public void setPhoto_url(String photo_url) {
//        this.photo_url = photo_url;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Integer getManager() {
//        return manager;
//    }
//
//    public void setManager(Integer manager) {
//        this.manager = manager;
//    }
//}
