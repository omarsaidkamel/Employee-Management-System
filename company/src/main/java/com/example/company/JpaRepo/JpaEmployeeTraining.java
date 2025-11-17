package com.example.company.JpaRepo;

import com.example.company.entities.EmployeeTraining;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployeeTraining extends JpaRepository<EmployeeTraining, Integer> {
}
