package com.tsoa.digibank.exceptions.operation;

public class AccountOperationAlreadyValidatedException extends Exception{
    public AccountOperationAlreadyValidatedException() {
        super("Operation already validated");
    }
}
