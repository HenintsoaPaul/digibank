package com.tsoa.digibank.data.models;

import com.tsoa.digibank.data.enums.OperationStatus;
import com.tsoa.digibank.data.enums.OperationType;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private String description;
    private double amount;
    private OperationType type;
    private OperationStatus status;

    @ManyToOne
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "transfer_id")
    private TransferOperation transfer;
}
