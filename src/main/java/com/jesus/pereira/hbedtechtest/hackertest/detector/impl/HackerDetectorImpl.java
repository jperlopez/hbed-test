package com.jesus.pereira.hbedtechtest.hackertest.detector.impl;

import com.jesus.pereira.hbedtechtest.hackertest.attemptmanager.IAttemptManager;
import com.jesus.pereira.hbedtechtest.hackertest.detector.HackerDetector;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import com.jesus.pereira.hbedtechtest.hackertest.parser.IParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HackerDetectorImpl implements HackerDetector {

    private final IAttemptManager attemptManager;
    private final IParser<LogEntry> logEntryParser;

    @Override
    public String parseLine(String line) {

        LogEntry logEntry = logEntryParser.parse(line);

        return attemptManager.tooManyFailedAttempts(logEntry) ? logEntry.getIp() : null;
    }
}
