package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.DTO.NoticeDTO;
import com.example.demo.controller.NoticeController2;
import com.example.demo.service.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(NoticeController2.class)
public class NoticeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticeService noticeService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNotice() throws Exception {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(1);
        noticeDTO.setTitle("Notice Title");
        noticeDTO.setContent("Notice Content");

        when(noticeService.saveNotice(noticeDTO)).thenReturn(noticeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/notice/addNotices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noticeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void getNotices() throws Exception {
        NoticeDTO noticeDTO1 = new NoticeDTO();
        noticeDTO1.setId(1);
        noticeDTO1.setTitle("Notice 1");
        noticeDTO1.setContent("Content 1");

        NoticeDTO noticeDTO2 = new NoticeDTO();
        noticeDTO2.setId(2);
        noticeDTO2.setTitle("Notice 2");
        noticeDTO2.setContent("Content 2");

        when(noticeService.getAllNotices()).thenReturn(Arrays.asList(noticeDTO1, noticeDTO2));

        mockMvc.perform(MockMvcRequestBuilders.get("/notice/getNotices")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getNoticeById() throws Exception {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(1);
        noticeDTO.setTitle("Notice Title");
        noticeDTO.setContent("Notice Content");

        when(noticeService.getNoticeById(1)).thenReturn(Optional.of(noticeDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/notice/getNotice/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateNotice() throws Exception {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(1);
        noticeDTO.setTitle("Updated Title");
        noticeDTO.setContent("Updated Content");

        when(noticeService.updateNotice(1, noticeDTO)).thenReturn(noticeDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/notice/change/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(noticeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNotice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notice/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
