package com.tsoa.digibank.data.models;

import com.tsoa.digibank.data.enums.OperationStatus;
import com.tsoa.digibank.data.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private String description;
    private double amount;
    private OperationStatus status;

    @OneToMany(mappedBy = "transfer", cascade = CascadeType.ALL)
    private List<AccountOperation> operations = new ArrayList<>();

    public AccountOperation getDebit() {
        for (AccountOperation operation : operations) {
            if (operation.getType() == OperationType.DEBIT) return operation;
        }
        return null;
    }

    public AccountOperation getCredit() {
        for (AccountOperation operation : operations) {
            if (operation.getType() == OperationType.CREDIT) return operation;
        }
        return null;
    }
}
