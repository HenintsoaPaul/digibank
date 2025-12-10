package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.exceptions.BalanceNotSufficientException;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.exceptions.NegativeAmountException;

import java.util.List;

public interface OperationService {
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException, NegativeAmountException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException;

    List<AccountOperationDTO> accountHistory(String accountId);

//    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
