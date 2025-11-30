package com.tsoa.digibank.data.dtos.operation;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}
