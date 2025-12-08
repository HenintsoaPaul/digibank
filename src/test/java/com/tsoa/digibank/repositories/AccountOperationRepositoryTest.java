package com.tsoa.digibank.repositories;

import com.tsoa.digibank.data.models.AccountOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountOperationRepositoryTest {

    @Autowired
    private AccountOperationRepository accountOperationRepository;

    private String bankAccountId = "47065088-ab53-4572-b24a-b5949a425718";

    @Test
    void findByBankAccountId() {
        var operations = accountOperationRepository.findByBankAccountId(bankAccountId);
        assertNotNull(operations);

        // Numbers
        assertEquals(2, operations.size());

        // BankAccountId
        Predicate<AccountOperation> matchId = op -> op
                .getBankAccount().getId().equals(bankAccountId);

        assert (
                operations
                        .stream()
                        .allMatch(matchId)
        );
    }
}