package com.rabobank.accountservice.domain.exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {
        super();
    }
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
    public InsufficientFundsException(String message) {
        super(message);
    }
    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }
}
