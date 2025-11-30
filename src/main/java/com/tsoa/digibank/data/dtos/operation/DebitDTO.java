package com.tsoa.digibank.data.dtos.operation;

import lombok.Data;

@Data
public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;
}
