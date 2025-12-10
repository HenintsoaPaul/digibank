package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.data.models.bankaccount.BankAccount;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import com.tsoa.digibank.repositories.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private AccountOperationRepository  accountOperationRepository;

    @InjectMocks
    private OperationServiceImpl operationService;

    @Test
    void credit_shouldIncreaseBalance() throws Exception {
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

        // ... Also verify method calls of correct logic
        verify(bankAccountRepository).findById(id);
        verify(bankAccountRepository).save(bankAccount);
        verify(accountOperationRepository).save(any(AccountOperation.class));
    }
}