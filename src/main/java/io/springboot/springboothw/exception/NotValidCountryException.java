package io.springboot.springboothw.exception;

public class NotValidCountryException extends RuntimeException {

    public NotValidCountryException(String message) {
        super(message);
    }

}
