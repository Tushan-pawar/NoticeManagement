package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.NoticeDTO;
import com.example.demo.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = { "http://localhost:3000" })
@Validated
public class NoticeController2 {

    @Autowired
    private NoticeService noticeService;

    // Add a notice
    @PostMapping("/addNotices")
    public ResponseEntity<NoticeDTO> createNotice(@RequestBody NoticeDTO noticeDTO) {
        NoticeDTO createdNotice = noticeService.saveNotice(noticeDTO);
        return ResponseEntity.ok(createdNotice);
    }


    // Get all notices
    @GetMapping("/getNotices")
    public ResponseEntity<List<NoticeDTO>> getNotices() {
        List<NoticeDTO> noticeDTOs = noticeService.getAllNotices();
        return ResponseEntity.ok(noticeDTOs);
    }

    // Get a notice by ID
    @GetMapping("/getNotice/{id}")
    public ResponseEntity<NoticeDTO> getNoticeById(@PathVariable Integer id) {
        Optional<NoticeDTO> noticeDTO = noticeService.getNoticeById(id);
        return noticeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete by Id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotice(@PathVariable int id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.ok("Notice deleted successfully");
    }

    // Update notice by Id
    @PutMapping("/change/{id}")
    public ResponseEntity<NoticeDTO> updateNotice(@PathVariable Integer id, @RequestBody NoticeDTO noticeDTO) {
        NoticeDTO updatedNotice = noticeService.updateNotice(id, noticeDTO);
        return ResponseEntity.ok(updatedNotice);
    }
}
