package com.tsoa.digibank.data.models.bankaccount;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount {
    private double overdraftLimit;
}
