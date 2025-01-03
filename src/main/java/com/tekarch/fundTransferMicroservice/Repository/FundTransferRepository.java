package com.tekarch.fundTransferMicroservice.Repository;

import com.tekarch.fundTransferMicroservice.Model.FundTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransfer, Long> {

    // Retrieve fund transfer by transferId
    Optional<FundTransfer> findByTransferId(Long transferId);

    // Retrieve all fund transfers by account ID
    List<FundTransfer> findByFromAccountIdOrToAccountId(Long fromAccountId, Long toAccountId);

    // Retrieve all fund transfers by status
    List<FundTransfer> findByStatus(String status);

    // Add more custom queries as needed
}
