package com.tekarch.fundTransferMicroservice.Service.Interface;

import com.tekarch.fundTransferMicroservice.DTO.ScheduledTransferDTO;

public interface ScheduledTransferService {
    ScheduledTransferDTO scheduleTransfer(ScheduledTransferDTO scheduledTransferDto);
    ScheduledTransferDTO getScheduledTransferDetails(Long scheduleId);
    ScheduledTransferDTO updateScheduledTransfer(Long scheduleId, ScheduledTransferDTO scheduledTransferDto);
    // Add other relevant methods as required
}
