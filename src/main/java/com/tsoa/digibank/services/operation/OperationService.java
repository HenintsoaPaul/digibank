package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.data.dtos.operation.AccountOperationReq;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.exceptions.operation.AccountOperationAlreadyValidatedException;
import com.tsoa.digibank.exceptions.operation.AccountOperationNotFoundException;
import com.tsoa.digibank.exceptions.BalanceNotSufficientException;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.exceptions.NegativeAmountException;
import com.tsoa.digibank.exceptions.operation.TransferOperationAlreadyValidatedException;
import com.tsoa.digibank.exceptions.operation.TransferOperationNotFoundException;

import java.util.List;

public interface OperationService {
    AccountOperation debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException;

    AccountOperation credit(String accountId, double amount, String description) throws BankAccountNotFoundException, NegativeAmountException;

    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException;

    void validateDebit(Long operationId) throws AccountOperationNotFoundException, AccountOperationAlreadyValidatedException;

    void validateCredit(Long operationId) throws AccountOperationNotFoundException, AccountOperationAlreadyValidatedException;

    void validateTransfer(Long transferId) throws TransferOperationAlreadyValidatedException, TransferOperationNotFoundException, AccountOperationNotFoundException, AccountOperationAlreadyValidatedException;

    List<AccountOperationDTO> accountHistory(String accountId);

    int create(AccountOperationReq operationReq);

//    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
