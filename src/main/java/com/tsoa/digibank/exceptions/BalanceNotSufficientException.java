package com.tsoa.digibank.exceptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException() {
        super("Balance not sufficient");
    }
}
