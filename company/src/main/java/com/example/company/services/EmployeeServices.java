package com.example.company.services;

import com.example.company.JpaRepo.JpaEmployee;
import com.example.company.dto.EmployeeDTO;
import com.example.company.entities.Employees;
import com.example.company.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServices {

    private final JpaEmployee jpaEmployee;
    private final EmployeeMapper employeeMapper;

    public EmployeeServices(JpaEmployee jpaEmployee, EmployeeMapper employeeMapper) {
        this.jpaEmployee = jpaEmployee;
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDTO> getAllEmployees() {
        return jpaEmployee.findAll()
                .stream()
                .map(employeeMapper::toEmployeeDTO)
                .toList();
    }

    public Optional<EmployeeDTO> getEmployeeById(int id) {
        return jpaEmployee.findById(id)
                .map(employeeMapper::toEmployeeDTO);
    }

    public EmployeeDTO addEmployees(EmployeeDTO employee) {
        Employees employeeEntity = employeeMapper.toEmployeeEntity(employee);
        employeeEntity = jpaEmployee.save(employeeEntity);
        return employeeMapper.toEmployeeDTO(employeeEntity);
    }

    public EmployeeDTO updateEmployees(EmployeeDTO updatedEmployees) {
        Employees entity = employeeMapper.toEmployeeEntity(updatedEmployees);
        Employees saved = jpaEmployee.save(entity);
        return employeeMapper.toEmployeeDTO(saved);
    }

    public void deleteEmployees(int id) {
        if (jpaEmployee.existsById(id)) {
            jpaEmployee.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    public void deleteEmployees(List<Integer> ids) {
        jpaEmployee.deleteAllById(ids);
    }
}

