package com.tsoa.digibank.data.dtos.operation;

import lombok.Data;

/**
 * Sent from front-end to create a new Operation with status set to Pending
 */
@Data
public class AccountOperationReq {
    private double amount;
    private String type;
    private String description;
}
