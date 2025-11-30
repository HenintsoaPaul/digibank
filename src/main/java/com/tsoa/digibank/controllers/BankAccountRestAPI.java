package com.tsoa.digibank.controllers;

import com.tsoa.digibank.data.dtos.bankaccount.BankAccountDTO;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.services.bankaccount.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/accounts")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    @GetMapping("/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.bankAccountList();
    }

    /*
    Account history with pagination
     */
//    @GetMapping("/{accountId}/pageOperations")
//    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
//                                               @RequestParam(name = "page", defaultValue = "0") int page,
//                                               @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
//        return bankAccountService.getAccountHistory(accountId, page, size);
//    }

    /*
    Account history without pagination
     */
//    @GetMapping("/{idAccount}/operations")
//    public List<AccountOperationDTO> getHistory(@PathVariable String idAccount) {
//        return bankAccountService.accountHistory(idAccount);
//    }
}
