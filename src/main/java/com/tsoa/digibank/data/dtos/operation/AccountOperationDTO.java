package com.tsoa.digibank.data.dtos.operation;

import com.tsoa.digibank.data.enums.OperationStatus;
import com.tsoa.digibank.data.enums.OperationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountOperationDTO {
    private Long id;
    private LocalDateTime dateStr;
    private double amount;
    private OperationType type;
    private OperationStatus status;
    private String description;
}
