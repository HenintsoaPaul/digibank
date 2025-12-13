package com.tsoa.digibank.controllers;

import com.tsoa.digibank.data.dtos.operation.*;
import com.tsoa.digibank.exceptions.*;
import com.tsoa.digibank.services.operation.OperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/operations")
public class OperationController {
    private OperationService operationService;

    @PostMapping("/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException {
        operationService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping("/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException, NegativeAmountException {
        operationService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException, NegativeAmountException {
        operationService.transfer(transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody AccountOperationReq operationReq)  {
        int code = operationService.create(operationReq);
        if (code == 1) {
            return ResponseEntity.ok(operationReq);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }
}
