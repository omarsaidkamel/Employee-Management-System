package com.example.company.services;

import com.example.company.JpaRepo.JpaDepartment;
import com.example.company.JpaRepo.JpaEmployee;
import com.example.company.dto.DepartmentDTO;
import com.example.company.entities.Department;
import com.example.company.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.company.mapper.DepartmentMapper.toDepartmentDTO;
import static com.example.company.mapper.DepartmentMapper.toDepartmentEntity;

@Service
public class DepartmentServices {

    private final JpaDepartment jpaDepartment;
    private final JpaEmployee jpaEmployee;

    public DepartmentServices(JpaDepartment jpaDepartment, JpaEmployee jpaEmployee){
        this.jpaDepartment = jpaDepartment;
        this.jpaEmployee = jpaEmployee;
    }

    public List<DepartmentDTO> getAllDepartment() {
        return jpaDepartment.findAll()
                .stream()
                .map(DepartmentMapper::toDepartmentDTO)
                .toList();
    }

    // Get department by ID
    public Optional<DepartmentDTO> getDepartmentById(int id) {
        return jpaDepartment.findById(id)
                .map(DepartmentMapper::toDepartmentDTO);
    }

    // Add a new department
    public DepartmentDTO addDepartment(DepartmentDTO department) {
        return toDepartmentDTO(jpaDepartment.save(toDepartmentEntity(department)));
    }

    // Update an existing department
    public DepartmentDTO updateDepartment(DepartmentDTO updatedDepartment) {
        return toDepartmentDTO(jpaDepartment.save(toDepartmentEntity(updatedDepartment)));
    }

    // Delete a department by ID
    public void deleteDepartment(int id) {
        if (jpaDepartment.existsById(id)) {
            Department department = jpaDepartment.findById(id).orElse(null);
            if(department != null){
                department.getEmployees().forEach(employee -> {
                    employee.setDepartment(null);
                    jpaEmployee.save(employee);
                });
            jpaDepartment.deleteById(id);
            }
        } else {
            throw new RuntimeException("Department not found with id " + id);
        }
    }

}
