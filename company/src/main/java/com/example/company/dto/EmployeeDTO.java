package com.example.company.dto;

import java.util.Date;
import java.util.List;

public class EmployeeDTO {

    private Integer employeeID;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private int age;
    private String job;
    private String employeeImage;
    private Date creationDate;
    private Boolean active;
    private String email;

    private Integer departmentID;
    private String departmentName;

    // Attachments (list of AttachmentDTO)
    private List<Integer> attachmentsIDs;
    private List<String> attachmentsPaths;


    private List<Integer> trainingCourse;


    public List<Integer> getTrainingCourse() {
        return trainingCourse;
    }

    public void setTrainingCourse(List<Integer> trainingCourse) {
        this.trainingCourse = trainingCourse;
    }

// ----------- Getters & Setters -----------

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(String employeeImage) {
        this.employeeImage = employeeImage;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Integer> getAttachmentsIDs() {
        return attachmentsIDs;
    }

    public void setAttachmentsIDs(List<Integer> attachmentsIDs) {
        this.attachmentsIDs = attachmentsIDs;
    }

    public List<String> getAttachmentsPaths() {
        return attachmentsPaths;
    }

    public void setAttachmentsPaths(List<String> attachmentsPaths) {
        this.attachmentsPaths = attachmentsPaths;
    }
    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }


}
