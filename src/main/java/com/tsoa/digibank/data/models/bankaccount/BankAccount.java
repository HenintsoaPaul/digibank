package com.tsoa.digibank.data.models.bankaccount;

import com.tsoa.digibank.data.enums.AccountStatus;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.data.models.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Date operationDate;
    private double balance;
    private AccountStatus status;

    @ManyToOne
    private Customer customer;

    // Many operations for a single bank account
    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}
