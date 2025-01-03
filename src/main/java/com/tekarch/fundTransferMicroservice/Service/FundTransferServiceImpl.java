package com.tekarch.fundTransferMicroservice.Service;

import com.tekarch.fundTransferMicroservice.DTO.FundTransferRequestDTO;
import com.tekarch.fundTransferMicroservice.DTO.FundTransferResponseDTO;
import com.tekarch.fundTransferMicroservice.Model.FundTransfer;
import com.tekarch.fundTransferMicroservice.Repository.FundTransferRepository;
import com.tekarch.fundTransferMicroservice.Service.Interface.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    @Autowired
    private FundTransferRepository fundTransferRepository;

    @Autowired
    private RestTemplate restTemplate;  // Inject RestTemplate for calling the Account Service

    private static final String ACCOUNT_SERVICE_URL = "http://localhost:8081/accounts";  // URL to Account Service

    // Method to initiate a fund transfer
    @Override
    public FundTransferResponseDTO initiateTransfer(FundTransferRequestDTO transferRequest) {
        // Validate Account IDs via Account Service
        if (!validateAccount(transferRequest.getFromAccountId()) || !validateAccount(transferRequest.getToAccountId())) {
            return new FundTransferResponseDTO(
                    null,
                    transferRequest.getFromAccountId(),
                    transferRequest.getToAccountId(),
                    transferRequest.getAmount(),
                    "Invalid account IDs",
                    400 // Status code for invalid request
            );
        }

        // Create a FundTransfer model from the request DTO
        FundTransfer fundTransfer = new FundTransfer();
        fundTransfer.setFromAccountId(transferRequest.getFromAccountId());
        fundTransfer.setToAccountId(transferRequest.getToAccountId());
        fundTransfer.setAmount(transferRequest.getAmount());

        // Save transfer to the database
        FundTransfer savedTransfer = fundTransferRepository.save(fundTransfer);

        // Map FundTransfer model to FundTransferResponseDTO
        return new FundTransferResponseDTO(
                savedTransfer.getTransferId(),
                savedTransfer.getFromAccountId(),
                savedTransfer.getToAccountId(),
                savedTransfer.getAmount(),
                "Transfer initiated successfully",
                200 // Status code for successful transfer
        );
    }

    // Method to get transfer details by transfer ID
    @Override
    public FundTransferResponseDTO getTransferDetails(Long transferId) {
        // Fetch the transfer from the database
        FundTransfer fundTransfer = fundTransferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Transfer not found"));

        // Map FundTransfer model to FundTransferResponseDTO
        return new FundTransferResponseDTO(
                fundTransfer.getTransferId(),
                fundTransfer.getFromAccountId(),
                fundTransfer.getToAccountId(),
                fundTransfer.getAmount(),
                "Transfer details retrieved successfully",
                200 // Status code for successful retrieval
        );
    }

    // Method to validate account IDs
    private boolean validateAccount(Long accountId) {
        // Construct the URL to fetch account details from Account Service
        String url = ACCOUNT_SERVICE_URL + "/" + accountId;

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