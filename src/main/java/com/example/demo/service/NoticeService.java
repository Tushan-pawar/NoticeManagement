package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.NoticeDTO;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.model.Notice;
import com.example.demo.repository.NoticeRepository;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    private NoticeDTO convertDTO(Notice notice) {
        NoticeDTO dto = new NoticeDTO();
        dto.setId(notice.getId());
        dto.setContent(notice.getContent());
        dto.setTitle(notice.getTitle());
        return dto;
    }

    private Notice convertEntity(NoticeDTO dto) {
        Notice notice = new Notice();
        notice.setId(dto.getId());
        notice.setTitle(dto.getTitle());
        notice.setContent(dto.getContent());
        return notice;
    }

    public List<NoticeDTO> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(this::convertDTO)
                .toList();
    }

    public Optional<NoticeDTO> getNoticeById(Integer id) {
        return noticeRepository.findById(id)
                .map(this::convertDTO);
    }

    public NoticeDTO saveNotice(NoticeDTO noticeDTO) {
        Notice notice = convertEntity(noticeDTO);
        Notice savedNotice = noticeRepository.save(notice);
        return convertDTO(savedNotice);
    }

    public void deleteNotice(Integer id) {
        if (!noticeRepository.existsById(id)) {
            throw new InvalidIdException("Notice not found with ID: " + id);
        }
        noticeRepository.deleteById(id);
    }

    public NoticeDTO updateNotice(Integer id, NoticeDTO noticeDTO) {
        Notice existingNotice = noticeRepository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Notice not found with ID: " + id));
        existingNotice.setTitle(noticeDTO.getTitle());
        existingNotice.setContent(noticeDTO.getContent());
        Notice updatedNotice = noticeRepository.save(existingNotice);
        return convertDTO(updatedNotice);
    }
}
