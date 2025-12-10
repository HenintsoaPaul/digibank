package com.tsoa.digibank.exceptions.operation;

public class TransferOperationAlreadyValidatedException extends Exception{
    public TransferOperationAlreadyValidatedException() {
        super("Transfer already validated");
    }
}
