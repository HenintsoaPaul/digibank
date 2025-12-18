package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.commands.operations.CreateOperationCommand;
import com.tsoa.digibank.data.enums.OperationType;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.events.AccountOperationCreatedEvent;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateOperationHandler {
    private final AccountOperationRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public CreateOperationHandler(AccountOperationRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public AccountOperationCreatedEvent handle(CreateOperationCommand command) throws IllegalArgumentException {
        // 1. Validate business rules
        validate(command);

        // 2. Map to entity
        AccountOperation operation = mapToEntity(command);

        // 3. Persist
        repository.save(operation);

        // 4. Publish event for side effects
        var event = new AccountOperationCreatedEvent(this, operation.getId());
        eventPublisher.publishEvent(event);
        return event;
    }

    private AccountOperation mapToEntity(CreateOperationCommand command) {
        AccountOperation operation = new AccountOperation();
        operation.setAmount(command.getAmount());
        operation.setType(OperationType.fromLabel(command.getType()));
        operation.setDescription(command.getDescription());
        return operation;
    }

    private void validate(CreateOperationCommand command) throws IllegalArgumentException {
        // Validate amount
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Validate type
        if (!command.getType().equals("DEBIT") && !command.getType().equals("CREDIT")) {
            throw new IllegalArgumentException("Invalid operation type");
        }

        // Sanitize description
        if (command.getDescription() == null || command.getDescription().trim().isEmpty()) {
            command.setDescription("No description provided");
        }
    }
}
