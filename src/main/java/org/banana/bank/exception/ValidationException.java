package org.banana.bank.exception;

import org.springframework.validation.BindingResult;

/**
 * Exception for indicating that some input or constraint is invalid.
 */
public class ValidationException extends RuntimeException {

    private final BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
