package com.tekarch.fundTransferMicroservice.Repository;

import com.tekarch.fundTransferMicroservice.Model.TransferLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferLimitRepository extends JpaRepository<TransferLimit, Long> {

    // Retrieve transfer limit by accountId
    Optional<TransferLimit> findByAccountId(Long accountId);


    // Add other custom queries as needed
}