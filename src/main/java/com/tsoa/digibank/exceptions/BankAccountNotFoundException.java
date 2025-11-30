package com.tsoa.digibank.exceptions;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException() {
        super("BankAccount not found");
    }
}
