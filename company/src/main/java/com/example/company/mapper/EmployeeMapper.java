package com.example.company.mapper;

import com.example.company.JpaRepo.JpaAttachments;
import com.example.company.JpaRepo.JpaDepartment;
import com.example.company.JpaRepo.JpaEmployeeTraining;
import com.example.company.dto.EmployeeDTO;
import com.example.company.entities.Attachments;
import com.example.company.entities.Department;
import com.example.company.entities.EmployeeTraining;
import com.example.company.entities.Employees;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EmployeeMapper {

    private final JpaAttachments jpaAttachments;
    private final JpaDepartment jpaDepartment;
    private final JpaEmployeeTraining jpaEmployeeTraining;

    public EmployeeMapper(JpaAttachments jpaAttachments, JpaDepartment jpaDepartment, JpaEmployeeTraining jpaEmployeeTraining) {
        this.jpaEmployeeTraining = jpaEmployeeTraining;
        this.jpaAttachments = jpaAttachments;
        this.jpaDepartment = jpaDepartment;
    }

    // -------------------- Entity → DTO --------------------
    public EmployeeDTO toEmployeeDTO(Employees employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmployeeID(employee.getEmployeeID());
        dto.setUsername(employee.getUsername());
        dto.setPassword(employee.getPassword());
        dto.setFullName(employee.getFullName());
        dto.setGender(employee.getGender());
        dto.setAge(employee.getAge());
        dto.setJob(employee.getJob());
        dto.setEmployeeImage(employee.getEmployeeImage());
        dto.setCreationDate(employee.getCreationDate());
        dto.setActive(employee.getActive());
        dto.setEmail(employee.getEmail());

        if (employee.getDepartment() != null) {
            dto.setDepartmentID(employee.getDepartment().getDepartmentID());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        if (employee.getAttachmentsList() != null) {
            dto.setAttachmentsPaths(
                    employee.getAttachmentsList()
                            .stream()
                            .map(Attachments::getFilePath)
                            .toList()
            );
            dto.setAttachmentsIDs(
                    employee.getAttachmentsList()
                            .stream()
                            .map(Attachments::getId)
                            .toList()
            );
        }
        if (employee.getEmployeeTraining() != null) {
           dto.setTrainingCourse(
                   employee.getEmployeeTraining()
                           .stream()
                           .map(EmployeeTraining::getId)
                           .toList()
           );
        }
        return dto;
    }

    // -------------------- DTO → Entity --------------------
    public Employees toEmployeeEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employees employee = new Employees();
        if (dto.getEmployeeID() != null) {
            employee.setEmployeeID(dto.getEmployeeID());
        }

        employee.setUsername(dto.getUsername());
        employee.setPassword(dto.getPassword());
        employee.setFullName(dto.getFullName());
        employee.setGender(dto.getGender());
        employee.setAge(dto.getAge());
        employee.setJob(dto.getJob());
        employee.setEmployeeImage(dto.getEmployeeImage());
        employee.setCreationDate(dto.getCreationDate());
        employee.setActive(dto.getActive());
        employee.setEmail(dto.getEmail());

        if (dto.getDepartmentID() != null) {
            Department dept = jpaDepartment.findById(dto.getDepartmentID()).orElse(null);
            employee.setDepartment(dept);
        }

        if (dto.getAttachmentsIDs() != null && !dto.getAttachmentsIDs().isEmpty()) {
            List<Attachments> attachments = dto.getAttachmentsIDs()
                    .stream()
                    .map(id -> jpaAttachments.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();

            attachments.forEach(a -> a.setEmployee(employee));
            employee.setAttachmentsList(attachments);
        }
        if (dto.getTrainingCourse() != null && !dto.getTrainingCourse().isEmpty()) {
            List<EmployeeTraining> trainings = dto.getTrainingCourse()
                    .stream()
                    .map(id -> jpaEmployeeTraining.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .toList();
        }
        return employee;
    }
}
