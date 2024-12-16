package io.springboot.springboothw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotValidCountryException.class)
    public ResponseEntity<ExceptionResponse> handleNotValidCountryException(NotValidCountryException e) {
        return new ResponseEntity<>(
                new ExceptionResponse(400, "not valid country"),
                HttpStatus.BAD_REQUEST
        );
    }

}
