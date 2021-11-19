package com.avijit.assessment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException(final String message) {
    super(message);
  }
}
