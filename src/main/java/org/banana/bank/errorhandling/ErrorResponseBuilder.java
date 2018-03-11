package org.banana.bank.errorhandling;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import static org.banana.bank.constant.ValidationMessages.APOSTROPHE;
import static org.banana.bank.constant.ValidationMessages.COLON;
import static org.banana.bank.constant.ValidationMessages.DOT;
import static org.banana.bank.constant.ValidationMessages.ERRORS;
import static org.banana.bank.constant.ValidationMessages.FIELD;
import static org.banana.bank.constant.ValidationMessages.SPACE;
import static org.banana.bank.constant.ValidationMessages.VALIDATION_FAILED;

/**
 * Builder for ErrorResponse.
 */
public final class ErrorResponseBuilder {

    /**
     * Build result from errors.
     *
     * @param errors Errors
     *
     * @return Initiated ErrorResponse object
     */
    public static ErrorResponse build(Errors errors) {
        ErrorResponse error = new ErrorResponse(VALIDATION_FAILED + errors.getErrorCount() + ERRORS);

        for (ObjectError objectError : errors.getAllErrors()) {
            String fieldName = FIELD + APOSTROPHE + ((FieldError) objectError).getField() + APOSTROPHE + COLON + SPACE;
            error.addValidationError(fieldName + objectError.getDefaultMessage() + DOT);
        }

        return error;
    }
}