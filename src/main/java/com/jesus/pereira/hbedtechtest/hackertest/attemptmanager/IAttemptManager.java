package com.jesus.pereira.hbedtechtest.hackertest.attemptmanager;

import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;

public interface IAttemptManager {
    boolean tooManyFailedAttempts(LogEntry logEntry);
    void resetAttemptManager();
}

