package com.example.company.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class TrainingCoursesDTO {
    private Integer id;
    private String courseCode;
    private String courseTitle;
    private String provider;
    private Integer durationHours;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Instant createdAt;
    private List<Integer> employeeTrainings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getEmployeeTrainings() {
        return employeeTrainings;
    }

    public void setEmployeeTrainings(List<Integer> employeeTrainings) {
        this.employeeTrainings = employeeTrainings;
    }
}
