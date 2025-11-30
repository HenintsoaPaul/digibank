package com.tsoa.digibank.data.models;

//import com.fasterxml.jackson.annotation.JsonProperty;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private Date createdAt;
    private String name;
    private String email;

    // Customer can have many bank accounts
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BankAccount> bankAccounts;
}
