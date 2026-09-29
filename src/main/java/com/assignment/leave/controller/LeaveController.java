package com.assignment.leave.controller;

import com.assignment.leave.dto.LeaveRequestDto;
import com.assignment.leave.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequestDto> createLeave(@Valid @RequestBody LeaveRequestDto requestDto) {
        return new ResponseEntity<>(leaveService.createLeave(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequestDto>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequestDto> updateLeave(@PathVariable Long id, @Valid @RequestBody LeaveRequestDto requestDto) {
        return ResponseEntity.ok(leaveService.updateLeave(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}