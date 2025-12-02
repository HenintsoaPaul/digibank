package com.tsoa.digibank.controllers;

import com.tsoa.digibank.data.dtos.bankaccount.BankAccountDTO;
import com.tsoa.digibank.data.dtos.operation.AccountOperationDTO;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.services.bankaccount.BankAccountService;
import com.tsoa.digibank.services.operation.OperationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class BankAccountController {
    private BankAccountService bankAccountService;
    private OperationService operationService;

    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    /*
    Account history == all operations for an account
     */
    @GetMapping("/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return operationService.accountHistory(accountId);
    }
}
