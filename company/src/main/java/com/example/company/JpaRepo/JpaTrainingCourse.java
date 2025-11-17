package com.example.company.JpaRepo;

import com.example.company.entities.TrainingCourses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTrainingCourse extends JpaRepository<TrainingCourses, Integer> {
}
