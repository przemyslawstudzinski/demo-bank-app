package org.banana.bank.errorhandling;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object represents the results of the error response.
 */
public class ErrorResponse {

    @Getter
    private final String message;

    @Getter
    private List<String> errors;

    /**
     * Creates new error response with message.
     *
     * @param message message displayed to the user
     *
     */
    public ErrorResponse(String message) {
        this.message = message;
        errors = new ArrayList<>();
    }

    public void addValidationError(String error) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(error);
    }
}
