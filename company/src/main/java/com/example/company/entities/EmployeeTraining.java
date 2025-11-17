package com.example.company.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;

@Entity
public class EmployeeTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employees employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private TrainingCourses course;

    @ColumnDefault("sysutcdatetime()")
    @Column(name = "enrolled_at", nullable = false)
    private Instant enrolledAt;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @ColumnDefault("'ENROLLED'")
    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Nationalized
    @Column(name = "grade", length = 20)
    private String grade;

    @Column(name = "hours_earned")
    private Integer hoursEarned;

    @Nationalized
    @Column(name = "notes", length = 400)
    private String notes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public TrainingCourses getCourse() {
        return course;
    }

    public void setCourse(TrainingCourses course) {
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