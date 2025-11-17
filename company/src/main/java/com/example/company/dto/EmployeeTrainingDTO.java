package com.example.company.dto;

import com.example.company.entities.Employees;
import com.example.company.entities.TrainingCourses;
import java.time.Instant;
import java.time.LocalDate;

public class EmployeeTrainingDTO {
    private Integer id;
    private Integer employee;
    private Integer course;
    private Instant enrolledAt;
    private LocalDate completionDate;
    private String status;
    private String grade;
    private Integer hoursEarned;
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Instant getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Instant enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getHoursEarned() {
        return hoursEarned;
    }

    public void setHoursEarned(Integer hoursEarned) {
        this.hoursEarned = hoursEarned;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
