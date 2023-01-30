package com.jesus.pereira.hbedtechtest.timecalculationtest.exception;

public class ErrorParsingDatesException extends RuntimeException {

    public ErrorParsingDatesException(String message, Throwable err) {
        super(message, err);
    }
}
