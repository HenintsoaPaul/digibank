package com.tsoa.digibank.repositories;

import com.tsoa.digibank.data.models.AccountOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountOperationRepositoryTest {

    @Autowired
    private AccountOperationRepository accountOperationRepository;

    @Test
    void findByBankAccountId_returnsExpectedOperations() {
        String bankAccountId = "47065088-ab53-4572-b24a-b5949a425718";
        List<AccountOperation> operations = accountOperationRepository.findByBankAccountId(bankAccountId);

        assertAll("basic collection checks",
                () -> assertNotNull(operations, "Repository returned null (should return empty list when none)."),
                () -> assertFalse(operations.isEmpty(), "Expected at least one operation for bankAccountId=" + bankAccountId),
                () -> assertEquals(2, operations.size(), "Expected 2 operations for bankAccountId=" + bankAccountId)
        );

        // Verify per-operation invariants and that each operation references the expected bank account id
        Predicate<AccountOperation> hasExpectedBankAccount = op -> op.getBankAccount() != null
                && bankAccountId.equals(op.getBankAccount().getId());

        assertAll("per-operation invariants",
                () -> assertTrue(
                        operations.stream().allMatch(hasExpectedBankAccount),
                        "All returned operations must reference bankAccount id=" + bankAccountId
                ),

                () -> assertTrue(
                        operations.stream().noneMatch(op -> op.getId() == null),
                        "All operations should have a non-null id"
                ),

                () -> assertTrue(
                        operations.stream().noneMatch(op -> Double.isNaN(op.getAmount())),
                        "All operations should have a valid (non-NaN) amount"
                ),

                () -> assertTrue(
                        operations.stream().noneMatch(op -> op.getType() == null),
                        "All operations should have a non-null type"
                )
        );

        // Ensure there are no duplicate operation ids returned
        Set<Long> ids = operations.stream().map(AccountOperation::getId).collect(Collectors.toSet());
        assertEquals(operations.size(), ids.size(), "Operation ids should be unique");
    }

    @Test
    void findByBankAccountId_withUnknownId_returnsEmptyList() {
        String unknownId = "00000000-0000-0000-0000-000000000000";
        List<AccountOperation> operations = accountOperationRepository.findByBankAccountId(unknownId);

        assertAll("unknown id behavior",
                () -> assertNotNull(operations, "Repository should return an empty list rather than null for unknown id"),
                () -> assertTrue(operations.isEmpty(), "Expected empty list for unknown bankAccountId=" + unknownId)
        );
    }
}