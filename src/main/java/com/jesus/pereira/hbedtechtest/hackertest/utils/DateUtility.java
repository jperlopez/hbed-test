package com.jesus.pereira.hbedtechtest.hackertest.utils;

import com.jesus.pereira.hbedtechtest.hackertest.exception.LogEntryParseException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class DateUtility {

    public static LocalDateTime parseEpoch(String epoch){
        try {
            long epochInMilliseconds= Long.valueOf(epoch) * 1000;
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochInMilliseconds), ZoneId.systemDefault());
        } catch (NumberFormatException e) {
            throw new LogEntryParseException("Invalid epoch format: " + epoch, e);
        }
    }
}
