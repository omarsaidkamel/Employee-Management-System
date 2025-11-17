package com.example.company.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeID;
    private String username;
    private String password;
    private String fullName;
    private String gender;
    private int age;
    private String  job;
    private String  employeeImage;
    private Date creationDate;
    private Boolean active;
    private String email;
    @ManyToOne
    @JoinColumn(name =  "departmentID")
    private Department department;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Attachments> attachmentsList;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<EmployeeTraining> employeeTraining;

    public List<EmployeeTraining> getEmployeeTraining() {
        return employeeTraining;
    }

    public void setEmployeeTraining(List<EmployeeTraining> employeeTraining) {
        this.employeeTraining = employeeTraining;
    }

    public List<Attachments> getAttachmentsList() {
        return attachmentsList;
    }

    public void setAttachmentsList(List<Attachments> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }


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

    public void setAge(int age) {this.age = age;}

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
