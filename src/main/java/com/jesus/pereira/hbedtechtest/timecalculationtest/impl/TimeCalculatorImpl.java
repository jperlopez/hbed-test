package com.jesus.pereira.hbedtechtest.timecalculationtest.impl;

import com.jesus.pereira.hbedtechtest.timecalculationtest.ITimeCalculator;
import com.jesus.pereira.hbedtechtest.timecalculationtest.exception.ErrorParsingDatesException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
public class TimeCalculatorImpl implements ITimeCalculator {

    @Value("${timecalculator.rfc2822pattern}")
    private String rfc2822Pattern;

    @Override
    public int getMinutesBetweenDates(String rfc2822Date1, String rfc2822Date2) {

        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        try {
            ZonedDateTime date1 = ZonedDateTime.parse(rfc2822Date1, formatter);
            ZonedDateTime date2 = ZonedDateTime.parse(rfc2822Date2, formatter);
            return Math.abs(Math.toIntExact(Duration.between(date1,date2).toMinutes()));
        } catch (DateTimeParseException e) {
            throw new ErrorParsingDatesException("Error parsing dates",e);
        }
    }
}
