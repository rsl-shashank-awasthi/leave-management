package com.assignment.leave.service;

import com.assignment.leave.dto.LeaveRequestDto;
import com.assignment.leave.exception.ResourceNotFoundException;
import com.assignment.leave.model.LeaveRequest;
import com.assignment.leave.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public LeaveRequestDto createLeave(LeaveRequestDto dto) {
        LeaveRequest request = mapToEntity(dto);
        request.setStatus("PENDING");
        LeaveRequest saved = leaveRepository.save(request);
        return mapToDto(saved);
    }

    public List<LeaveRequestDto> getAllLeaves() {
        return leaveRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public LeaveRequestDto getLeaveById(Long id) {
        LeaveRequest request = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));
        return mapToDto(request);
    }

    public LeaveRequestDto updateLeave(Long id, LeaveRequestDto dto) {
        LeaveRequest existing = leaveRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Leave request not found with id: " + id));

        existing.setLeaveType(dto.getLeaveType());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setReason(dto.getReason());
        if (dto.getStatus() != null) {
            existing.setStatus(dto.getStatus());
        }

        LeaveRequest updated = leaveRepository.save(existing);
        return mapToDto(updated);
    }

    public void deleteLeave(Long id) {
        if (!leaveRepository.deleteById(id)) {
            throw new ResourceNotFoundException("Leave request not found with id: " + id);
        }
    }

    private LeaveRequest mapToEntity(LeaveRequestDto dto) {
        return LeaveRequest.builder()
                .id(dto.getId())
                .employeeId(dto.getEmployeeId())
                .leaveType(dto.getLeaveType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .build();
    }

    private LeaveRequestDto mapToDto(LeaveRequest entity) {
        return LeaveRequestDto.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .leaveType(entity.getLeaveType())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .reason(entity.getReason())
                .status(entity.getStatus())
                .build();
    }
}