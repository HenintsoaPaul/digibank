package com.tsoa.digibank.exceptions.operation;

public class TransferOperationNotFoundException extends Exception{
    public TransferOperationNotFoundException() {
        super("Transfer not found");
    }
}
