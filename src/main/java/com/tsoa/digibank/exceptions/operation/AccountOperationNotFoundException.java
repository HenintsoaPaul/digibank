package com.tsoa.digibank.exceptions.operation;

public class AccountOperationNotFoundException extends Exception{
    public AccountOperationNotFoundException() {
        super("Operation not found");
    }
}
