package com.example.company.JpaRepo;

import com.example.company.entities.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAttachments extends JpaRepository<Attachments, Integer> {
}
