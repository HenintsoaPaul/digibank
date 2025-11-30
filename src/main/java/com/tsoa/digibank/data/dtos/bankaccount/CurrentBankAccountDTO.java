package com.tsoa.digibank.data.dtos.bankaccount;

import lombok.Data;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO {
    private double overDraft;
}
