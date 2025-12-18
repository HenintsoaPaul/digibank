package com.tsoa.digibank.controllers;

import com.tsoa.digibank.data.commands.operations.CreateOperationCommand;
import com.tsoa.digibank.data.dtos.operation.*;
import com.tsoa.digibank.events.AccountOperationCreatedEvent;
import com.tsoa.digibank.exceptions.*;
import com.tsoa.digibank.responses.ApiBadResponse;
import com.tsoa.digibank.responses.ApiOkResponse;
import com.tsoa.digibank.responses.ApiResponse;
import com.tsoa.digibank.services.operation.CreateOperationHandler;
import com.tsoa.digibank.services.operation.OperationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/operations")
public class OperationController {
    private OperationService operationService;

    @PostMapping("/debit")
    public ApiResponse<DebitDTO> debit(@RequestBody DebitDTO debitDTO) {
        String accountId = debitDTO.getAccountId();
        try {
            operationService.debit(accountId, debitDTO.getAmount(), debitDTO.getDescription());
            String msg = "Account " + accountId + " debited successfully";
            return new ApiOkResponse<>(msg, debitDTO);
        } catch (BankAccountNotFoundException | BalanceNotSufficientException | NegativeAmountException e) {
            return new ApiBadResponse<>(e.getMessage(), debitDTO);
        }
    }

    @PostMapping("/credit")
    public ApiResponse<CreditDTO> credit(@RequestBody CreditDTO creditDTO) {
        String accountId = creditDTO.getAccountId();
        try {
            operationService.credit(accountId, creditDTO.getAmount(), creditDTO.getDescription());
            String msg = "Account " + accountId + " credited successfully";
            return new ApiOkResponse<>(msg, creditDTO);
        } catch (BankAccountNotFoundException | NegativeAmountException e) {
            return new ApiBadResponse<>(e.getMessage(), creditDTO);
        }
    }

    @PostMapping("/transfer")
    public ApiResponse<TransferRequestDTO> transfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        try {
            operationService.transfer(transferRequestDTO.getAccountSource(),
                    transferRequestDTO.getAccountDestination(),
                    transferRequestDTO.getAmount());
            return new ApiOkResponse<>("Transfer created successfully.", transferRequestDTO);
        } catch (BankAccountNotFoundException | BalanceNotSufficientException | NegativeAmountException e) {
            return new ApiBadResponse<>(e.getMessage(), transferRequestDTO);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody AccountOperationReq operationReq) {
        int code = operationService.create(operationReq);
        if (code == 1) {
            return ResponseEntity.ok(operationReq);
        } else {
            return ResponseEntity.internalServerError().build();
    public ApiResponse<CreateOperationCommand> create(@RequestBody CreateOperationCommand command) {
        try {
            AccountOperationCreatedEvent event = createOperationHandler.handle(command);
            return new ApiOkResponse<>(event.getMessage(), command);
        } catch (IllegalArgumentException e) {
            return new ApiBadResponse<>(e.getMessage(), command);
        }
    }
}
