package com.assignment.leave.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {

    private Long id;

    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @NotBlank(message = "Leave Type is required")
    private String leaveType;

    @NotNull(message = "Start Date is required")
    @FutureOrPresent(message = "Start Date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End Date is required")
    @FutureOrPresent(message = "End Date cannot be in the past")
    private LocalDate endDate;

    @NotBlank(message = "Reason is required")
    private String reason;

    private String status;
}