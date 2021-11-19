package com.avijit.assessment.util;

import com.avijit.assessment.exception.ErrorDetails;
import com.avijit.assessment.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@RestControllerAdvice
@Slf4j
public class ExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(HttpServletRequest request , Exception ex) {
        this.printLog(ex);
        final ErrorDetails error =
                new ErrorDetails(
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI(),
                        ex.getMessage());

        return buildResponseEntity(error);
    }

    void printLog(Exception e) {
        log.error(e.getMessage());
    }

    private ResponseEntity<ErrorDetails> buildResponseEntity(ErrorDetails errorDetails) {
        return ResponseEntity.status(errorDetails.getCode()).body(errorDetails);
    }
}
