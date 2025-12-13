package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.data.dtos.operation.AccountOperationReq;
import com.tsoa.digibank.data.enums.OperationStatus;
import com.tsoa.digibank.data.enums.OperationType;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.data.models.TransferOperation;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import com.tsoa.digibank.exceptions.operation.AccountOperationAlreadyValidatedException;
import com.tsoa.digibank.exceptions.operation.AccountOperationNotFoundException;
import com.tsoa.digibank.exceptions.BalanceNotSufficientException;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.exceptions.NegativeAmountException;
import com.tsoa.digibank.exceptions.operation.TransferOperationAlreadyValidatedException;
import com.tsoa.digibank.exceptions.operation.TransferOperationNotFoundException;
import com.tsoa.digibank.mappers.AppMapper;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import com.tsoa.digibank.repositories.BankAccountRepository;
import com.tsoa.digibank.repositories.TransferOperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OperationServiceImpl implements OperationService {
    private TransferOperationRepository transferOperationRepository;
    private AppMapper dtoMapper;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;

    /**
     * Initialize an account operation without any type
     */
    private AccountOperation initOp(BankAccount bankAccount, double amount, String description) {
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setStatus(OperationStatus.PENDING);
        return accountOperation;
    }

    @Override
    public AccountOperation debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException();

        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(BankAccountNotFoundException::new);

        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException();

        AccountOperation accountOperation = initOp(bankAccount, amount, description);
        accountOperation.setType(OperationType.DEBIT);
        accountOperationRepository.save(accountOperation);

        return accountOperation;
    }

    @Override
    public AccountOperation credit(String accountId, double amount, String description) throws BankAccountNotFoundException, NegativeAmountException {
        if (amount < 0)
            throw new NegativeAmountException();

        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(BankAccountNotFoundException::new);

        AccountOperation accountOperation = initOp(bankAccount, amount, description);
        accountOperation.setType(OperationType.CREDIT);
        accountOperationRepository.save(accountOperation);

        return accountOperation;
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException {
        AccountOperation debit = debit(accountIdSource, amount, "Transfer to " + accountIdDestination),
                credit = credit(accountIdDestination, amount, "Transfer from " + accountIdSource);

        TransferOperation transferOperation = new TransferOperation();
        transferOperation.setAmount(amount);
        transferOperation.getOperations().add(debit);
        transferOperation.getOperations().add(credit);
        transferOperation.setOperationDate(new Date());
        transferOperation.setStatus(OperationStatus.PENDING);
        transferOperation.setDescription("Transfer from " + accountIdSource + " to " + accountIdDestination);

        transferOperationRepository.save(transferOperation);
    }

    @Override
    public void validateDebit(Long operationId) throws AccountOperationNotFoundException, AccountOperationAlreadyValidatedException {
        AccountOperation operation = accountOperationRepository.findById(operationId)
                .orElseThrow(AccountOperationNotFoundException::new);

        if (operation.getStatus() == OperationStatus.VALIDATED)
            throw new AccountOperationAlreadyValidatedException();

        BankAccount bankAccount = operation.getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() - operation.getAmount());
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void validateCredit(Long operationId) throws AccountOperationNotFoundException, AccountOperationAlreadyValidatedException {
        AccountOperation operation = accountOperationRepository.findById(operationId)
                .orElseThrow(AccountOperationNotFoundException::new);

        if (operation.getStatus() == OperationStatus.VALIDATED)
            throw new AccountOperationAlreadyValidatedException();

        BankAccount bankAccount = operation.getBankAccount();
        bankAccount.setBalance(bankAccount.getBalance() + operation.getAmount());
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void validateTransfer(Long transferId) throws TransferOperationAlreadyValidatedException, TransferOperationNotFoundException, AccountOperationNotFoundException, AccountOperationAlreadyValidatedException {
        TransferOperation transfer = transferOperationRepository.findById(transferId)
                .orElseThrow(TransferOperationNotFoundException::new);

        if (transfer.getStatus() == OperationStatus.VALIDATED)
            throw new TransferOperationAlreadyValidatedException();

        validateDebit(transfer.getDebit().getId());
        validateCredit(transfer.getCredit().getId());
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String accountId) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream()
                .map(op -> dtoMapper.fromAccountOperation(op))
                .collect(Collectors.toList());
    }

    @Override
    public int create(AccountOperationReq operationReq) {
        if (operationReq.getAmount() == 500)
            return 1;
        else
            return 0;

        // Verifications
        // 1- amount
        // 2- type
        // 3- sanitize desc

        // Map to AccountOperation
    }

//    @Override
//    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
//        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);

//        if (bankAccount == null) throw new BankAccountNotFoundException("Account not found");
//        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
//        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
//
//        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
//        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
//        accountHistoryDTO.setAccountId(bankAccount.getId());
//        accountHistoryDTO.setBalance(bankAccount.getBalance());
//        accountHistoryDTO.setCurrentPage(page);
//        accountHistoryDTO.setPageSize(size);
//        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());

//        return accountHistoryDTO;
//    }
}
