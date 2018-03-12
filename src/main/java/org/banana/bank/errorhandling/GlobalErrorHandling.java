package org.banana.bank.errorhandling;

import org.banana.bank.exception.BadTokenException;
import org.banana.bank.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityNotFoundException;

import static org.banana.bank.constant.ValidationMessages.INTERNAL_SERVER_ERROR;

/**
 * ErrorHandling for application.
 */
@ControllerAdvice
public class GlobalErrorHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandling.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResponse(INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleBindingResultException(ValidationException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return ErrorResponseBuilder.build(exception.getBindingResult());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse entityNotFoundException(EntityNotFoundException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(BadTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleBadTokenException(BadTokenException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
