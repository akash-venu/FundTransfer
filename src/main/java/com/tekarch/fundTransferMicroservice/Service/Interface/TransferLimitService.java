package com.tekarch.fundTransferMicroservice.Service.Interface;

import com.tekarch.fundTransferMicroservice.DTO.TransferLimitDTO;

public interface TransferLimitService {
    TransferLimitDTO validateTransactionLimit(Long accountId, double amount);
    TransferLimitDTO getRemainingLimits(Long accountId);
    // Add other relevant methods as required
}