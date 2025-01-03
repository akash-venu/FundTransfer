package com.tekarch.fundTransferMicroservice.DTO;


public class TransferLimitDTO {
    private Long accountId;
    private double dailyLimitRemaining;
    private double weeklyLimitRemaining;
    private double monthlyLimitRemaining;
    private boolean valid;
    private String message;

    // Default constructor
    public TransferLimitDTO() {}

    // Getters and Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getDailyLimitRemaining() {
        return dailyLimitRemaining;
    }

    public void setDailyLimitRemaining(double dailyLimitRemaining) {
        this.dailyLimitRemaining = dailyLimitRemaining;
    }

    public double getWeeklyLimitRemaining() {
        return weeklyLimitRemaining;
    }

    public void setWeeklyLimitRemaining(double weeklyLimitRemaining) {
        this.weeklyLimitRemaining = weeklyLimitRemaining;
    }

    public double getMonthlyLimitRemaining() {
        return monthlyLimitRemaining;
    }

    public void setMonthlyLimitRemaining(double monthlyLimitRemaining) {
        this.monthlyLimitRemaining = monthlyLimitRemaining;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}