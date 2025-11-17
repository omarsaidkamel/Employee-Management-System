package com.example.company.mapper;

import com.example.company.JpaRepo.*;
import com.example.company.dto.EmployeeTrainingDTO;
import com.example.company.entities.EmployeeTraining;
import com.example.company.entities.Employees;
import com.example.company.entities.TrainingCourses;

public class EmployeeTrainingMapper {

    private final JpaEmployee jpaEmployee;
    private final JpaTrainingCourse jpaTrainingCourse;

    public EmployeeTrainingMapper(JpaEmployee jpaEmployee,JpaTrainingCourse jpaTrainingCourse) {
        this.jpaEmployee = jpaEmployee;
        this.jpaTrainingCourse = jpaTrainingCourse;
    }

    // Convert Entity → DTO
    public EmployeeTrainingDTO toDTO(EmployeeTraining entity) {
        if (entity == null) {
            return null;
        }

        EmployeeTrainingDTO dto = new EmployeeTrainingDTO();
        dto.setId(entity.getId());
        dto.setEmployee(entity.getEmployee() != null ? entity.getEmployee().getEmployeeID() : null);
        dto.setCourse(entity.getCourse() != null ? entity.getCourse().getId() : null);
        dto.setEnrolledAt(entity.getEnrolledAt());
        dto.setCompletionDate(entity.getCompletionDate());
        dto.setStatus(entity.getStatus());
        dto.setGrade(entity.getGrade());
        dto.setHoursEarned(entity.getHoursEarned());
        dto.setNotes(entity.getNotes());
        return dto;
    }

    // Convert DTO → Entity
    public EmployeeTraining toEntity(EmployeeTrainingDTO dto) {
        if (dto == null) {
            return null;
        }

        EmployeeTraining entity = new EmployeeTraining();
        entity.setId(dto.getId());
        entity.setEnrolledAt(dto.getEnrolledAt());
        entity.setCompletionDate(dto.getCompletionDate());
        entity.setStatus(dto.getStatus());
        entity.setGrade(dto.getGrade());
        entity.setHoursEarned(dto.getHoursEarned());
        entity.setNotes(dto.getNotes());

        if (dto.getEmployee() != null) {
            Employees employee = jpaEmployee.findById(dto.getEmployee()).orElse(null);
            entity.setEmployee(employee);
        }

        if (dto.getCourse() != null) {
            TrainingCourses course = jpaTrainingCourse.findById(dto.getCourse()).orElse(null);
            entity.setCourse(course);
        }

        return entity;
    }
}
