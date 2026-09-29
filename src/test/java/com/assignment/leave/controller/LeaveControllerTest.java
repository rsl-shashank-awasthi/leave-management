package com.assignment.leave.controller;

import com.assignment.leave.dto.LeaveRequestDto;
import com.assignment.leave.service.LeaveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeaveController.class)
class LeaveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LeaveService leaveService;

    @Test
    void createLeave_ValidRequest_ReturnsCreated() throws Exception {
        LeaveRequestDto requestDto = LeaveRequestDto.builder()
                .employeeId("EMP001")
                .leaveType("SICK")
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(3))
                .reason("Doctor Appointment")
                .build();

        LeaveRequestDto responseDto = requestDto;
        responseDto.setId(1L);
        responseDto.setStatus("PENDING");

        when(leaveService.createLeave(any(LeaveRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/leaves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void createLeave_InvalidRequest_ReturnsBadRequest() throws Exception {
        LeaveRequestDto invalidRequest = LeaveRequestDto.builder().build(); // Missing fields

        mockMvc.perform(post("/leaves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}