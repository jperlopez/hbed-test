package com.jesus.pereira.hbedtechtest.hackertest.parser.impl;

import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import com.jesus.pereira.hbedtechtest.hackertest.parser.IParser;
import com.jesus.pereira.hbedtechtest.hackertest.utils.DateUtility;
import com.jesus.pereira.hbedtechtest.hackertest.validator.IValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LogEntryParser implements IParser<LogEntry> {

    private final IValidator<List<String>> logEntryValidator;

    @Override
    public LogEntry parse(String line) {
        List<String> logEntryData = Stream.of(line.split(",", -1))
                .collect(Collectors.toList());

        logEntryValidator.validate(logEntryData);

        return LogEntry.builder()
                .ip(logEntryData.get(0))
                .entryDateTime(DateUtility.parseEpoch(logEntryData.get(1)))
                .loginAction(LoginActionEnum.valueOf(logEntryData.get(2)))
                .userName(logEntryData.get(3))
                .build();
    }

}
