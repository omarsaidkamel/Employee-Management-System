package com.example.company.controller;

import com.example.company.dto.AttachmentDTO;
import com.example.company.services.AttachmentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    private final AttachmentServices attachmentServices;

    public AttachmentController(AttachmentServices attachmentServices) {
        this.attachmentServices = attachmentServices;
    }

    @PostMapping
    public AttachmentDTO addAttachment(@RequestBody AttachmentDTO attachmentDTO) {
        return attachmentServices.addAttachment(attachmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttachment(@PathVariable int id) {
        attachmentServices.deleteAttachment(id);
    }

    @GetMapping("/{id}")
    public AttachmentDTO getAttachmentById(@PathVariable int id) {
        return attachmentServices.getAttachmentById(id);
    }

    @GetMapping
    public List<AttachmentDTO> getAllAttachments() {
        return attachmentServices.getAllAttachments();
    }
}
