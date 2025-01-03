package com.tekarch.fundTransferMicroservice.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer_limits")
public class TransferLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "limit_id")
    private Long limitId;

    @Column(name = "account_id", nullable = false, unique = true)
    private Long accountId; // Foreign Key from Account Service

    @Column(name = "daily_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal dailyLimit;

    @Column(name = "weekly_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal weeklyLimit;

    @Column(name = "monthly_limit", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyLimit;

    @Column(name = "daily_limit_remaining", precision = 15, scale = 2)
    private BigDecimal dailyLimitRemaining;

    @Column(name = "weekly_limit_remaining", precision = 15, scale = 2)
    private BigDecimal weeklyLimitRemaining;

    @Column(name = "monthly_limit_remaining", precision = 15, scale = 2)
    private BigDecimal monthlyLimitRemaining;

    @Column(name = "daily_used", precision = 15, scale = 2)
    private BigDecimal dailyUsed = BigDecimal.ZERO; // To track used amount for today

    @Column(name = "weekly_used", precision = 15, scale = 2)
    private BigDecimal weeklyUsed = BigDecimal.ZERO; // To track used amount for the week

    @Column(name = "monthly_used", precision = 15, scale = 2)
    private BigDecimal monthlyUsed = BigDecimal.ZERO; // To track used amount for the month

    // Getters and Setters

    public Long getLimitId() {
        return limitId;
    }

    public void setLimitId(Long limitId) {
        this.limitId = limitId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public BigDecimal getWeeklyLimit() {
        return weeklyLimit;
    }

    public void setWeeklyLimit(BigDecimal weeklyLimit) {
        this.weeklyLimit = weeklyLimit;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public BigDecimal getDailyLimitRemaining() {
        return dailyLimitRemaining;
    }

    public void setDailyLimitRemaining(BigDecimal dailyLimitRemaining) {
        this.dailyLimitRemaining = dailyLimitRemaining;
    }

    public BigDecimal getWeeklyLimitRemaining() {
        return weeklyLimitRemaining;
    }

    public void setWeeklyLimitRemaining(BigDecimal weeklyLimitRemaining) {
        this.weeklyLimitRemaining = weeklyLimitRemaining;
    }

    public BigDecimal getMonthlyLimitRemaining() {
        return monthlyLimitRemaining;
    }

    public void setMonthlyLimitRemaining(BigDecimal monthlyLimitRemaining) {
        this.monthlyLimitRemaining = monthlyLimitRemaining;
    }

    public BigDecimal getDailyUsed() {
        return dailyUsed;
    }

    public void setDailyUsed(BigDecimal dailyUsed) {
        this.dailyUsed = dailyUsed;
    }

    public BigDecimal getWeeklyUsed() {
        return weeklyUsed;
    }

    public void setWeeklyUsed(BigDecimal weeklyUsed) {
        this.weeklyUsed = weeklyUsed;
    }

    public BigDecimal getMonthlyUsed() {
        return monthlyUsed;
    }

    public void setMonthlyUsed(BigDecimal monthlyUsed) {
        this.monthlyUsed = monthlyUsed;
    }

    // Utility Methods to update limits
    public void updateDailyUsed(BigDecimal amount) {
        this.dailyUsed = this.dailyUsed.add(amount);
    }

    public void updateWeeklyUsed(BigDecimal amount) {
        this.weeklyUsed = this.weeklyUsed.add(amount);
    }

    public void updateMonthlyUsed(BigDecimal amount) {
        this.monthlyUsed = this.monthlyUsed.add(amount);
    }

    public void updateRemainingLimits() {
        this.dailyLimitRemaining = this.dailyLimit.subtract(this.dailyUsed);
        this.weeklyLimitRemaining = this.weeklyLimit.subtract(this.weeklyUsed);
        this.monthlyLimitRemaining = this.monthlyLimit.subtract(this.monthlyUsed);
    }
}