package com.tekarch.fundTransferMicroservice.Service;

import com.tekarch.fundTransferMicroservice.DTO.ScheduledTransferDTO;
import com.tekarch.fundTransferMicroservice.Model.ScheduledTransfer;
import com.tekarch.fundTransferMicroservice.Repository.ScheduledTransferRepository;
import com.tekarch.fundTransferMicroservice.Service.Interface.ScheduledTransferService;
import com.tekarch.fundTransferMicroservice.Exception.TransferNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ScheduledTransferServiceImpl implements ScheduledTransferService {

    @Autowired
    private ScheduledTransferRepository scheduledTransferRepository;

    @Autowired
    private RestTemplate restTemplate;  // Inject RestTemplate for calling the Account Service

    private static final String ACCOUNT_SERVICE_URL = "http://localhost:8081/accounts";  // URL to Account Service

    @Override
    public ScheduledTransferDTO scheduleTransfer(ScheduledTransferDTO scheduledTransferDto) {
        // Validate Account IDs via Account Service
        if (!validateAccount(scheduledTransferDto.getFromAccountId()) || !validateAccount(scheduledTransferDto.getToAccountId())) {
            throw new IllegalArgumentException("Invalid account IDs provided");
        }

        // Map DTO to Entity
        ScheduledTransfer scheduledTransfer = new ScheduledTransfer();
        scheduledTransfer.setFromAccountId(scheduledTransferDto.getFromAccountId());
        scheduledTransfer.setToAccountId(scheduledTransferDto.getToAccountId());
        scheduledTransfer.setAmount(scheduledTransferDto.getAmount());
        scheduledTransfer.setFrequency(scheduledTransferDto.getFrequency());
        scheduledTransfer.setStartDate(scheduledTransferDto.getStartDate());
        scheduledTransfer.setEndDate(scheduledTransferDto.getEndDate());
        scheduledTransfer.setDescription(scheduledTransferDto.getDescription());
        scheduledTransfer.setCreatedAt(LocalDateTime.now());

        // Save Entity
        ScheduledTransfer savedTransfer = scheduledTransferRepository.save(scheduledTransfer);

        // Map Entity to DTO for Response
        ScheduledTransferDTO response = mapEntityToDto(savedTransfer);
        return response;
    }

    @Override
    public ScheduledTransferDTO getScheduledTransferDetails(Long scheduleId) {
        // Fetch ScheduledTransfer Entity
        ScheduledTransfer scheduledTransfer = scheduledTransferRepository.findById(scheduleId)
                .orElseThrow(() -> new TransferNotFoundException("Scheduled transfer not found for ID: " + scheduleId));

        // Map Entity to DTO for Response
        return mapEntityToDto(scheduledTransfer);
    }

    @Override
    public ScheduledTransferDTO updateScheduledTransfer(Long scheduleId, ScheduledTransferDTO scheduledTransferDto) {
        // Fetch ScheduledTransfer Entity
        ScheduledTransfer existingTransfer = scheduledTransferRepository.findById(scheduleId)
                .orElseThrow(() -> new TransferNotFoundException("Scheduled transfer not found for ID: " + scheduleId));

        // Update fields
        if (scheduledTransferDto.getAmount() != null) {
            existingTransfer.setAmount(scheduledTransferDto.getAmount());
        }
        if (scheduledTransferDto.getEndDate() != null) {
            existingTransfer.setEndDate(scheduledTransferDto.getEndDate());
        }
        if (scheduledTransferDto.getDescription() != null) {
            existingTransfer.setDescription(scheduledTransferDto.getDescription());
        }

        // Save updated entity
        ScheduledTransfer updatedTransfer = scheduledTransferRepository.save(existingTransfer);

        // Map Entity to DTO for Response
        return mapEntityToDto(updatedTransfer);
    }

    // Utility method to map Entity to DTO
    private ScheduledTransferDTO mapEntityToDto(ScheduledTransfer scheduledTransfer) {
        ScheduledTransferDTO dto = new ScheduledTransferDTO();
        dto.setFromAccountId(scheduledTransfer.getFromAccountId());
        dto.setToAccountId(scheduledTransfer.getToAccountId());
        dto.setAmount(scheduledTransfer.getAmount());
        dto.setFrequency(scheduledTransfer.getFrequency());
        dto.setStartDate(scheduledTransfer.getStartDate());
        dto.setEndDate(scheduledTransfer.getEndDate());
        dto.setDescription(scheduledTransfer.getDescription());
        dto.setCreatedAt(scheduledTransfer.getCreatedAt());
        return dto;
    }

    // Method to validate account IDs
    private boolean validateAccount(Long accountId) {
        // Construct the URL to fetch account details from Account Service
        String url = ACCOUNT_SERVICE_URL + "http://localhost:8081/accounts" + accountId;

        try {
            // Use RestTemplate to make a GET request to Account Service
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            // If status code is 2xx, then account is valid
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Handle errors (invalid account, service not available, etc.)
            return false;
        }
    }
}