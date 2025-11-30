package com.tsoa.digibank.services.bankaccount;

import com.tsoa.digibank.data.dtos.bankaccount.BankAccountDTO;
import com.tsoa.digibank.data.dtos.bankaccount.CurrentBankAccountDTO;
import com.tsoa.digibank.data.dtos.bankaccount.SavingBankAccountDTO;
import com.tsoa.digibank.exceptions.*;

import java.util.List;

public interface BankAccountService {

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    List<BankAccountDTO> bankAccountList();

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
}
