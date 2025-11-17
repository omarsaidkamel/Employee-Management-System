package com.example.company.JpaRepo;

import com.example.company.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartment extends JpaRepository<Department, Integer> {
}
