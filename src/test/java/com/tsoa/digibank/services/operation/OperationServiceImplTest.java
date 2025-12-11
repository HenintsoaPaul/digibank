package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import com.tsoa.digibank.exceptions.BankAccountNotFoundException;
import com.tsoa.digibank.exceptions.NegativeAmountException;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import com.tsoa.digibank.repositories.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AccountOperationRepository repository;

    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    void credit_shouldIncreaseBalance() throws NegativeAmountException, BankAccountNotFoundException {
        String id = "1L";
        double initialAmount = 100;

        // Given
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(id);
        bankAccount.setBalance(initialAmount);

        // Stub repository response
        when(bankAccountRepository.findById(id))
                .thenReturn(Optional.of(bankAccount));
        when(bankAccountRepository.save(any(BankAccount.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        double amount = 500;
        String description = "test credit 1";
        operationService.credit(id, amount, description);

        // Then verify data
        assertEquals(
                initialAmount + amount,
                bankAccount.getBalance()
        );

        // ...also verify method calls of correct logic
        verify(bankAccountRepository).findById(id);
        verify(bankAccountRepository).save(bankAccount);
        verify(repository).save(any(AccountOperation.class));
    }

    @Test
    void debit_shouldThrowIfAccountNotFound() {
        assertThrows(
                BankAccountNotFoundException.class,
                () -> operationService.debit("10L", 100, "desc")
        );
    }

    @Test
    void debit_shouldThrowIfNegativeAmount() {
        assertThrows(
                NegativeAmountException.class,
                () -> operationService.debit("10L", -100, "desc")
        );
    }
}