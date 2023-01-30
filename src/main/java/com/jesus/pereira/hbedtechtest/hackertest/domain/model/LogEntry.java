package com.jesus.pereira.hbedtechtest.hackertest.domain.model;

import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogEntry {
    private String ip;
    private LocalDateTime entryDateTime;
    private LoginActionEnum loginAction;
    private String userName;
}
