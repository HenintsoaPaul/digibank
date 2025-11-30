package com.tsoa.digibank.data.models;

import com.tsoa.digibank.data.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Date createdAt;
    private String description;
    private double amount;
    private OperationType type;

    @ManyToOne
    private BankAccount bankAccount;
}
