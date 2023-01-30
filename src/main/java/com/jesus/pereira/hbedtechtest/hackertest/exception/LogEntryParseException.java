package com.jesus.pereira.hbedtechtest.hackertest.exception;

public class LogEntryParseException extends RuntimeException {

    public LogEntryParseException(String message, Throwable err) {
        super(message, err);
    }
}
