package com.example.company.controller;
import com.example.company.dto.DepartmentDTO;
import com.example.company.services.DepartmentServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    DepartmentServices departmentServices;

    DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @GetMapping("/department")
    public List<DepartmentDTO> getAllDepartment(){
        return departmentServices.getAllDepartment();
    }

    @PostMapping("/department")
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO department){
         return departmentServices.addDepartment(department);
    }

    @PutMapping("/department")
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO department){
         return departmentServices.updateDepartment(department);
    }

    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable("id") int id){
        departmentServices.deleteDepartment(id);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") int id) {
        Optional<DepartmentDTO> department = departmentServices.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
