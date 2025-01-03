package com.tekarch.fundTransferMicroservice.Service;

import com.tekarch.fundTransferMicroservice.DTO.TransferLimitDTO;
import com.tekarch.fundTransferMicroservice.Service.Interface.TransferLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransferLimitServiceImpl implements TransferLimitService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ACCOUNT_SERVICE_URL = "http://localhost:8081/accounts";

    private static final double DAILY_LIMIT = 10000.00; // Example daily limit
    private static final double WEEKLY_LIMIT = 50000.00; // Example weekly limit
    private static final double MONTHLY_LIMIT = 200000.00; // Example monthly limit

    private double dailyUsed = 0.0;
    private double weeklyUsed = 0.0;
    private double monthlyUsed = 0.0;

    @Override
    public TransferLimitDTO validateTransactionLimit(Long accountId, double amount) {
        // Validate account with Account Service
        if (!validateAccount(accountId)) {
            throw new IllegalArgumentException("Invalid account ID: " + accountId);
        }

        TransferLimitDTO response = new TransferLimitDTO();
        response.setAccountId(accountId);

        if (amount > DAILY_LIMIT - dailyUsed) {
            response.setValid(false);
            response.setMessage("Transaction exceeds daily limit.");
        } else if (amount > WEEKLY_LIMIT - weeklyUsed) {
            response.setValid(false);
            response.setMessage("Transaction exceeds weekly limit.");
        } else if (amount > MONTHLY_LIMIT - monthlyUsed) {
            response.setValid(false);
            response.setMessage("Transaction exceeds monthly limit.");
        } else {
            response.setValid(true);
            response.setMessage("Transaction is within the allowed limit.");
        }

        return response;
    }

    @Override
    public TransferLimitDTO getRemainingLimits(Long accountId) {
        // Validate account with Account Service
        if (!validateAccount(accountId)) {
            throw new IllegalArgumentException("Invalid account ID: " + accountId);
        }

        TransferLimitDTO response = new TransferLimitDTO();
        response.setAccountId(accountId);
        response.setDailyLimitRemaining(DAILY_LIMIT - dailyUsed);
        response.setWeeklyLimitRemaining(WEEKLY_LIMIT - weeklyUsed);
        response.setMonthlyLimitRemaining(MONTHLY_LIMIT - monthlyUsed);

        return response;
    }

    public void updateLimitUsage(double amount) {
        dailyUsed += amount;
        weeklyUsed += amount;
        monthlyUsed += amount;
    }

    // Method to validate account using Account Service
    private boolean validateAccount(Long accountId) {
        String url = ACCOUNT_SERVICE_URL + accountId;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Handle errors (invalid account, service not available, etc.)
            return false;
        }
    }
}