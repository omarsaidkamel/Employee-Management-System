package com.example.company.dto;

import java.util.List;

public class DepartmentDTO {

    private Integer departmentID;
    private String departmentName;

    private List<Integer> employeeIDs;

    // --- Getters & Setters ---
    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }


    public List<Integer> getEmployeeIDs() {
        return employeeIDs;
    }

    public void setEmployeeIDs(List<Integer> employeeIDs) {
        this.employeeIDs = employeeIDs;
    }
}
