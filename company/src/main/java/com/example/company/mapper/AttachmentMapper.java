package com.example.company.mapper;

import com.example.company.JpaRepo.JpaEmployee;
import com.example.company.dto.AttachmentDTO;
import com.example.company.entities.Attachments;
import com.example.company.entities.Employees;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

    private final JpaEmployee jpaEmployee;

    public AttachmentMapper(JpaEmployee jpaEmployee) {
        this.jpaEmployee = jpaEmployee;
    }

    // -------------------- Entity → DTO --------------------
    public AttachmentDTO toDTO(Attachments entity) {
        if (entity == null) {
            return null;
        }

        AttachmentDTO dto = new AttachmentDTO();
        dto.setId(entity.getId());
        dto.setFilePath(entity.getFilePath());
        dto.setUploadDate(entity.getUploadDate());

        if (entity.getEmployee() != null) {
            dto.setEmployeeID(entity.getEmployee().getEmployeeID());
        }

        return dto;
    }

    // -------------------- DTO → Entity --------------------
    public Attachments toEntity(AttachmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Attachments entity = new Attachments();
        entity.setId(dto.getId());
        entity.setFilePath(dto.getFilePath());
        entity.setUploadDate(dto.getUploadDate());

        // Fetch employee by ID if available
        if (dto.getEmployeeID() != null) {
            Employees employee = jpaEmployee.findById(dto.getEmployeeID()).orElse(null);
            entity.setEmployee(employee);
        } else {
            entity.setEmployee(null);
        }

        return entity;
    }
}
