package com.tekarch.fundTransferMicroservice.Repository;

import com.tekarch.fundTransferMicroservice.Model.ScheduledTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransfer, Long> {

    // Retrieve scheduled transfers by account ID
    List<ScheduledTransfer> findByFromAccountId(Long fromAccountId);

    List<ScheduledTransfer> findByToAccountId(Long toAccountId);

    // Retrieve scheduled transfers by status
    List<ScheduledTransfer> findByStatus(String status);

    // Retrieve scheduled transfer by scheduleId
    Optional<ScheduledTransfer> findByScheduleId(Long scheduleId);

    // Add other custom queries as needed
}