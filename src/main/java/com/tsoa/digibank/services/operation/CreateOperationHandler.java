package com.tsoa.digibank.services.operation;

import com.tsoa.digibank.data.commands.operations.CreateOperationCommand;
import com.tsoa.digibank.data.enums.OperationType;
import com.tsoa.digibank.data.models.AccountOperation;
import com.tsoa.digibank.events.AccountOperationCreatedEvent;
import com.tsoa.digibank.repositories.AccountOperationRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateOperationHandler {
    private AccountOperationRepository repository;
    private ApplicationEventPublisher eventPublisher;

    public AccountOperationCreatedEvent handle(CreateOperationCommand command) throws IllegalArgumentException {
        // 1. Validate business rules
        OperationType type = validate(command);

        // 2. Map to entity
        AccountOperation operation = mapToEntity(command, type);

        // 3. Persist
        repository.save(operation);

        // 4. Publish event for side effects
        var event = new AccountOperationCreatedEvent(this, operation.getId());
        eventPublisher.publishEvent(event);
        return event;
    }

    private AccountOperation mapToEntity(CreateOperationCommand command, OperationType type) {
        AccountOperation operation = new AccountOperation();
        operation.setAmount(command.getAmount());
        operation.setType(type);
        operation.setDescription(command.getDescription());
        return operation;
    }

    private OperationType validate(CreateOperationCommand command) throws IllegalArgumentException {
        // Validate amount
        if (command.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        // Validate type
        OperationType type = OperationType.fromLabel(command.getType());

        // Sanitize description
        if (command.getDescription() == null || command.getDescription().trim().isEmpty()) {
            command.setDescription("No description provided");
        }

        return type;
    }
}
