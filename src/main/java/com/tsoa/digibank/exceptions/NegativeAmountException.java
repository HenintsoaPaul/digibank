package com.tsoa.digibank.exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException() {
        super("Negative amount");
    }
}
