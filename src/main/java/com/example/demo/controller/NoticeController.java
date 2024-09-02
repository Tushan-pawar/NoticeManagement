//package com.example.demo.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.DTO.NoticeDTO;
//import com.example.demo.exception.InvalidIdException;
//import com.example.demo.model.Notice;
//import com.example.demo.service.NoticeService;
//
//@RestController
//@RequestMapping("/notice")
//@CrossOrigin(origins = { "http://localhost:3000" })
//public class NoticeController {
//
//    @Autowired
//    private NoticeService noticeService;
//
//    @PostMapping("/post/add")
//    public ResponseEntity<NoticeDTO> insertNotice(@RequestBody NoticeDTO noticeDTO) {
//        Notice notice = new Notice();
//        notice.setTitle(noticeDTO.getTitle());
//        notice.setContent(noticeDTO.getContent());
//        Notice savedNotice = noticeService.saveNotice(notice);
//        return ResponseEntity.ok(convertToDTO(savedNotice));
//    }
//    
//    
//    @GetMapping("/get/{id}")
//    public ResponseEntity<NoticeDTO> getNoticeById(@PathVariable Integer id) {
//        Optional<Notice> notice = noticeService.getNoticeById(id);
//        if (notice.isPresent()) {
//            return ResponseEntity.ok(convertToDTO(notice.get()));
//        } else {
//            throw new InvalidIdException("Notice not found ");
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllNotices() {
//        List<Notice> notices = noticeService.getAllNotices();
//        List<NoticeDTO> noticeDTOs = new ArrayList<>();
//        for (Notice notice : notices) {
//            NoticeDTO noticeDTO = convertToDTO(notice);
//            noticeDTOs.add(noticeDTO);
//        }
//        return ResponseEntity.ok(noticeDTOs);
//    }
//
//    
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteNotice(@PathVariable Integer id) {
//        try {
//            noticeService.deleteNotice(id);
//            return ResponseEntity.ok("Notic deleted successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PutMapping("/change/{id}")
//    public ResponseEntity<NoticeDTO> updateNotice(@PathVariable Integer id, @RequestBody NoticeDTO updatedNoticeDTO) {
//        Optional<Notice> existingNoticeOptional = noticeService.getNoticeById(id);
//        if (existingNoticeOptional.isPresent()) {
//            Notice existingNotice = existingNoticeOptional.get();
//            existingNotice.setTitle(updatedNoticeDTO.getTitle());
//            existingNotice.setContent(updatedNoticeDTO.getContent());
//            Notice updatedNotice = noticeService.saveNotice(existingNotice);
//            return ResponseEntity.ok(convertToDTO(updatedNotice));
//        } else {
//            throw new InvalidIdException("Notice not found");
//        }
//    }
//
//    private NoticeDTO convertToDTO(Notice notice) {
//        NoticeDTO dto = new NoticeDTO();
//        dto.setId(notice.getId());
//        dto.setTitle(notice.getTitle());
//        dto.setContent(notice.getContent());
//        return dto;
//    }
//}
