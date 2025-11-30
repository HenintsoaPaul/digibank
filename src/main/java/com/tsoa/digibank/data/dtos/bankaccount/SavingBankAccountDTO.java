package com.tsoa.digibank.data.dtos.bankaccount;

import lombok.Data;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private double interestRate;
}
