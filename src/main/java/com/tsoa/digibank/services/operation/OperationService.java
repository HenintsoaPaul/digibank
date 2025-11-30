package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.exceptions.BalanceNotSufficientException;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface OperationService {
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<AccountOperationDTO> accountHistory(String accountId);

//    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
