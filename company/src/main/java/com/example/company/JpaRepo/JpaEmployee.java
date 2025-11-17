package com.example.company.JpaRepo;

import com.example.company.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmployee extends JpaRepository<Employees, Integer> {
}
