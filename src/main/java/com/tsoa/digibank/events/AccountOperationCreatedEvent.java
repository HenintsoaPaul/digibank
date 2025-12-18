package com.tsoa.digibank.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AccountOperationCreatedEvent extends ApplicationEvent {
    private final Long operationId;

    public AccountOperationCreatedEvent(Object source, Long operationId) {
        super(source);
        this.operationId = operationId;
    }

    public String getMessage() {
        return "Account operation created with ID: " + operationId;
    }
}
