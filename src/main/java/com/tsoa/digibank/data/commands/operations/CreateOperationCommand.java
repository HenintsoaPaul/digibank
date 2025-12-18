package com.tsoa.digibank.data.commands.operations;

import lombok.Data;

/**
 * Sent from front-end to create a new Operation with status set to Pending
 */
@Data
public class CreateOperationCommand {
    private double amount;
    private String type;
    private String description;
}
