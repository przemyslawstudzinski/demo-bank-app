package org.banana.bank.exception;

/**
 * Exception for indicating that token is not valid.
 */
public class BadTokenException extends RuntimeException {

    public BadTokenException(String message) {
        super(message);
    }

    public BadTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
