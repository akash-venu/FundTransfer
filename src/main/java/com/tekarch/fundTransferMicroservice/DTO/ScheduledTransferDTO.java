package com.tekarch.fundTransferMicroservice.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ScheduledTransferDTO {

    private Long scheduleId;
    private Long fromAccountId;
    private Long toAccountId;
    private BigDecimal amount;
    private String frequency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
    private LocalDateTime nextExecutionDate;
    private String status;
    private LocalDateTime createdAt;

    // No-arguments constructor
    public ScheduledTransferDTO() {
    }

    // All-arguments constructor
    public ScheduledTransferDTO(Long scheduleId, Long fromAccountId, Long toAccountId, BigDecimal amount,
                                String frequency, LocalDateTime startDate, LocalDateTime endDate,
                                String description, LocalDateTime nextExecutionDate, String status,
                                LocalDateTime createdAt) {
        this.scheduleId = scheduleId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.nextExecutionDate = nextExecutionDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    // (Existing getter and setter methods remain unchanged)

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNextExecutionDate() {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(LocalDateTime nextExecutionDate) {
        this.nextExecutionDate = nextExecutionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}