package com.example.company.services;

import com.example.company.JpaRepo.JpaAttachments;
import com.example.company.dto.AttachmentDTO;
import com.example.company.entities.Attachments;
import com.example.company.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentServices {

    private final JpaAttachments jpaAttachments;
    private final AttachmentMapper attachmentMapper;

    public AttachmentServices(JpaAttachments jpaAttachments, AttachmentMapper attachmentMapper) {
        this.jpaAttachments = jpaAttachments;
        this.attachmentMapper = attachmentMapper;
    }

    // -------------------- ADD NEW ATTACHMENT --------------------
    public AttachmentDTO addAttachment(AttachmentDTO dto) {
        Attachments entity = attachmentMapper.toEntity(dto);
        Attachments saved = jpaAttachments.save(entity);
        return attachmentMapper.toDTO(saved);
    }

    // -------------------- DELETE ATTACHMENT BY ID --------------------
    public void deleteAttachment(int id) {
        if (jpaAttachments.existsById(id)) {
            jpaAttachments.deleteById(id);
        } else {
            throw new RuntimeException("Attachment not found with id " + id);
        }
    }

    // -------------------- GET ONE BY ID --------------------
    public AttachmentDTO getAttachmentById(int id) {
        Optional<Attachments> attachmentOpt = jpaAttachments.findById(id);
        return attachmentOpt.map(attachmentMapper::toDTO).orElse(null);
    }

    // -------------------- GET ALL --------------------
    public List<AttachmentDTO> getAllAttachments() {
        return jpaAttachments.findAll()
                .stream()
                .map(attachmentMapper::toDTO)
                .toList();
    }
}
