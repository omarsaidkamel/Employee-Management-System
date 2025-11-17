package com.example.company.controller;

import com.example.company.dto.EmployeeDTO;
import com.example.company.services.EmployeeServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeServices employeeServices;

    EmployeeController(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployee(){
        return employeeServices.getAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") int id) {
        Optional<EmployeeDTO> employee = employeeServices.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employee")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employee){
        return employeeServices.addEmployees(employee);
    }

    @PutMapping("/employee")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee){
        return employeeServices.updateEmployees(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployeeById(@PathVariable("id") int id){
        employeeServices.deleteEmployees(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> deleteMultipleEmployees(@RequestBody List<Integer> ids) {
        employeeServices.deleteEmployees(ids);
        return ResponseEntity.ok("Employees deleted successfully");
    }
}
