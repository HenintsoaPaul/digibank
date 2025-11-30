package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.data.enums.OperationType;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import com.tsoa.digibank.exceptions.BalanceNotSufficientException;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.mappers.AppMapper;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import com.tsoa.digibank.repositories.BankAccountRepository;
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
        return accountOperation;
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(BankAccountNotFoundException::new);

        if (bankAccount.getBalance() < amount)
            throw new BalanceNotSufficientException();

        AccountOperation accountOperation = initOp(bankAccount, amount, description);
        accountOperation.setType(OperationType.DEBIT);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountId)
                .orElseThrow(BankAccountNotFoundException::new);

        AccountOperation accountOperation = initOp(bankAccount, amount, description);
        accountOperation.setType(OperationType.CREDIT);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(accountIdSource, amount, "Trasfert to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String accountId) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream()
                .map(op -> dtoMapper.fromAccountOperation(op))
                .collect(Collectors.toList());
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
