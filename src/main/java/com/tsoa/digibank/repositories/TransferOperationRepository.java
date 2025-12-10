package com.tsoa.digibank.repositories;

import com.tsoa.digibank.data.models.TransferOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferOperationRepository extends JpaRepository<TransferOperation, Long> {
}
