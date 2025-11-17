package com.example.company.mapper;

import com.example.company.JpaRepo.JpaEmployee;
import com.example.company.dto.DepartmentDTO;
import com.example.company.entities.Department;
import com.example.company.entities.Employees;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class DepartmentMapper {

    static JpaEmployee jpaEmployee;
    // -------------------- Entity → DTO --------------------
    public static DepartmentDTO toDepartmentDTO(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setDepartmentID(department.getDepartmentID());
        dto.setDepartmentName(department.getDepartmentName());

        // Extract employee IDs and names safely
        if (department.getEmployees() != null) {
            List<Integer> employeeIDs = department.getEmployees()
                    .stream()
                    .map(Employees::getEmployeeID)
                    .toList();
            dto.setEmployeeIDs(employeeIDs);
        }
        return dto;
    }

    // -------------------- DTO → Entity --------------------
    public static Department toDepartmentEntity(DepartmentDTO dto) {
        if (dto == null) return null;

        Department department = new Department();
        department.setDepartmentID(dto.getDepartmentID());
        department.setDepartmentName(dto.getDepartmentName());

        if (dto.getEmployeeIDs() != null && !dto.getEmployeeIDs().isEmpty()) {
            List<Employees> employees = dto.getEmployeeIDs()
                    .stream()
                    .map(id -> jpaEmployee.findById(id).orElse(null)) // Fetch from DB
                    .filter(Objects::nonNull)
                    .toList();
            department.setEmployees(employees);
        }

        return department;
    }

}
