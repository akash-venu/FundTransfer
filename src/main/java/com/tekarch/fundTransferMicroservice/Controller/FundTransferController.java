package com.tekarch.fundTransferMicroservice.Controller;

import com.tekarch.fundTransferMicroservice.DTO.FundTransferRequestDTO;
import com.tekarch.fundTransferMicroservice.DTO.FundTransferResponseDTO;
import com.tekarch.fundTransferMicroservice.DTO.TransferLimitDTO;
import com.tekarch.fundTransferMicroservice.Service.Interface.FundTransferService;
import com.tekarch.fundTransferMicroservice.Service.Interface.TransferLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transfers")
public class FundTransferController {

    @Autowired
    private FundTransferService fundTransferService;

    @Autowired
    private TransferLimitService transferLimitService;

    // Initiate a transfer (Intra-bank or Inter-bank)
    @PostMapping
    public ResponseEntity<FundTransferResponseDTO> initiateTransfer(@Valid @RequestBody FundTransferRequestDTO transferRequest) {
        try {
            // Call service layer to initiate the transfer
            FundTransferResponseDTO response = fundTransferService.initiateTransfer(transferRequest);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Handle exception and return error response
            FundTransferResponseDTO response = new FundTransferResponseDTO(
                    null, null, null, BigDecimal.ZERO, e.getMessage(), HttpStatus.BAD_REQUEST.value()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Get details of a transfer
    @GetMapping("/{transferId}")
    public ResponseEntity<FundTransferResponseDTO> getTransferDetails(@PathVariable Long transferId) {
        try {
            // Get transfer details from service layer
            FundTransferResponseDTO response = fundTransferService.getTransferDetails(transferId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            // Handle exception and return error response
            FundTransferResponseDTO response = new FundTransferResponseDTO(
                    null, null, null, BigDecimal.ZERO, e.getMessage(), HttpStatus.BAD_REQUEST.value()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Validate if the transaction is within the limits
    @GetMapping("/validate-transaction/{accountId}")
    public ResponseEntity<TransferLimitDTO> validateTransactionLimit(@PathVariable Long accountId, @RequestParam double amount) {
        try {
            TransferLimitDTO limitDTO = transferLimitService.validateTransactionLimit(accountId, amount);
            return ResponseEntity.status(HttpStatus.OK).body(limitDTO);
        } catch (Exception e) {
            TransferLimitDTO limitDTO = new TransferLimitDTO();
            limitDTO.setAccountId(null);
            limitDTO.setDailyLimitRemaining(0.0);
            limitDTO.setWeeklyLimitRemaining(0.0);
            limitDTO.setMonthlyLimitRemaining(0.0);
            limitDTO.setValid(false);
            limitDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(limitDTO);
        }
    }

    // Get remaining limits for a user account
    @GetMapping("/remaining-limits/{accountId}")
    public ResponseEntity<TransferLimitDTO> getRemainingLimits(@PathVariable Long accountId) {
        try {
            TransferLimitDTO limitDTO = transferLimitService.getRemainingLimits(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(limitDTO);
        } catch (Exception e) {
            TransferLimitDTO limitDTO = new TransferLimitDTO();
            limitDTO.setAccountId(null);
            limitDTO.setDailyLimitRemaining(0.0);
            limitDTO.setWeeklyLimitRemaining(0.0);
            limitDTO.setMonthlyLimitRemaining(0.0);
            limitDTO.setValid(false);
            limitDTO.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(limitDTO);
        }
    }
}