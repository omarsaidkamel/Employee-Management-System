package com.example.company.mapper;

import com.example.company.JpaRepo.JpaAttachments;
import com.example.company.JpaRepo.JpaDepartment;
import com.example.company.JpaRepo.JpaEmployeeTraining;
import com.example.company.JpaRepo.JpaTrainingCourse;
import com.example.company.dto.TrainingCoursesDTO;
import com.example.company.entities.Attachments;
import com.example.company.entities.EmployeeTraining;
import com.example.company.entities.TrainingCourses;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TrainingCoursesMapper {


    private final JpaEmployeeTraining jpaEmployeeTraining;

    public TrainingCoursesMapper(JpaEmployeeTraining jpaEmployeeTraining) {
        this.jpaEmployeeTraining = jpaEmployeeTraining;

    }
    // Convert Entity → DTO
    public TrainingCoursesDTO toDTO(TrainingCourses entity) {
        if (entity == null) {
            return null;
        }

        TrainingCoursesDTO dto = new TrainingCoursesDTO();
        dto.setId(entity.getId());
        dto.setCourseCode(entity.getCourseCode());
        dto.setCourseTitle(entity.getCourseTitle());
        dto.setProvider(entity.getProvider());
        dto.setDurationHours(entity.getDurationHours());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setActive(entity.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getEmployeeTrainings() != null) {
            dto.setEmployeeTrainings(
                    entity.getEmployeeTrainings()
                            .stream()
                            .map(EmployeeTraining::getId)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Convert DTO → Entity
    public TrainingCourses toEntity(TrainingCoursesDTO dto) {
        if (dto == null) {
            return null;
        }

        TrainingCourses entity = new TrainingCourses();
        entity.setId(dto.getId());
        entity.setCourseCode(dto.getCourseCode());
        entity.setCourseTitle(dto.getCourseTitle());
        entity.setProvider(dto.getProvider());
        entity.setDurationHours(dto.getDurationHours());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setIsActive(dto.getActive());
        entity.setCreatedAt(dto.getCreatedAt());

        if (entity.getEmployeeTrainings() != null && !entity.getEmployeeTrainings().isEmpty()) {
            List<EmployeeTraining> employeeTrainings = dto.getEmployeeTrainings()
                    .stream()
                    .map(id -> jpaEmployeeTraining.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();

            entity.setEmployeeTrainings(employeeTrainings);
        }
        return entity;
    }
}
