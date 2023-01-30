package com.jesus.pereira.hbedtechtest.hackertest.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class IpLoginAttempt {
    private String ipAddress;
    private LocalDateTime firstAttempt;
    private LocalDateTime lastAttempt;
    private int failedAttemtpsCounter;
}
