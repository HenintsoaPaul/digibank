package com.tsoa.digibank.data.dtos.operation;

import com.tsoa.digibank.data.enums.OperationType;
import lombok.Data;

import java.sql.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date date;
    private double amount;
    private OperationType type;
    private String description;
}
