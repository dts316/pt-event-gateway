package com.philantree.eventgateway.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException{
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public UserException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
