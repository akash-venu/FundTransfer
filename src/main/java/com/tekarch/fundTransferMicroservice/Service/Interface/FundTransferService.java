package com.tekarch.fundTransferMicroservice.Service.Interface;


import com.tekarch.fundTransferMicroservice.DTO.FundTransferRequestDTO;
import com.tekarch.fundTransferMicroservice.DTO.FundTransferResponseDTO;

public interface FundTransferService {

    // Method to initiate a fund transfer
    FundTransferResponseDTO initiateTransfer(FundTransferRequestDTO transferRequest);

    // Method to get transfer details by transfer ID
    FundTransferResponseDTO getTransferDetails(Long transferId);
}
