package com.assignment.leave.service;

import com.assignment.leave.dto.LeaveRequestDto;
import com.assignment.leave.exception.ResourceNotFoundException;
import com.assignment.leave.model.LeaveRequest;
import com.assignment.leave.repository.LeaveRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeaveServiceTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private LeaveService leaveService;

    @Test
    void createLeave_ShouldReturnSavedDto() {
        LeaveRequestDto inputDto = LeaveRequestDto.builder()
                .employeeId("EMP001").leaveType("SICK")
                .startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(2))
                .reason("Fever").build();

        LeaveRequest savedEntity = LeaveRequest.builder()
                .id(1L).employeeId("EMP001").status("PENDING").build();

        when(leaveRepository.save(any(LeaveRequest.class))).thenReturn(savedEntity);

        LeaveRequestDto result = leaveService.createLeave(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("PENDING", result.getStatus());
    }

    @Test
    void getLeaveById_WhenExists_ShouldReturnDto() {
        LeaveRequest entity = LeaveRequest.builder().id(1L).employeeId("EMP001").build();
        when(leaveRepository.findById(1L)).thenReturn(Optional.of(entity));

        LeaveRequestDto result = leaveService.getLeaveById(1L);

        assertEquals(1L, result.getId());
        assertEquals("EMP001", result.getEmployeeId());
    }

    @Test
    void getLeaveById_WhenNotExists_ShouldThrowException() {
        when(leaveRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> leaveService.getLeaveById(1L));
    }
}