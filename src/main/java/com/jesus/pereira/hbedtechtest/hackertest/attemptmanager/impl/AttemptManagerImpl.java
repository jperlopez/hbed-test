package com.jesus.pereira.hbedtechtest.hackertest.attemptmanager.impl;

import com.jesus.pereira.hbedtechtest.hackertest.attemptmanager.IAttemptManager;
import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.IpLoginAttempt;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AttemptManagerImpl implements IAttemptManager {

    private static Map<String, IpLoginAttempt> ipLoginAttemptsByIpMap = new HashMap<>();

    @Value("${attemptManager.attemptsPeriodInMinutes}")
    private int attemptPeriodInMinutes;

    @Value("${attemptManager.attemptsPeriodInMinutes}")
    private int maxLoginAttempts;

    @Override
    public boolean tooManyFailedAttempts(LogEntry logEntry) {

        if (LoginActionEnum.SIGNIN_SUCCESS.equals(logEntry.getLoginAction())) {
            return false;
        }

        if (ipLoginAttemptsByIpMap.containsKey(logEntry.getIp())) {
            updateIpAttempts(ipLoginAttemptsByIpMap.get(logEntry.getIp()), logEntry);
        }else{
            IpLoginAttempt ipLoginAttempt = IpLoginAttempt.builder()
                    .ipAddress(logEntry.getIp())
                    .firstAttempt(logEntry.getEntryDateTime())
                    .lastAttempt(logEntry.getEntryDateTime())
                    .failedAttemtpsCounter(1)
                    .build();
            ipLoginAttemptsByIpMap.put(logEntry.getIp(), ipLoginAttempt);
        }

        return ipLoginAttemptsByIpMap.get(logEntry.getIp()).getFailedAttemtpsCounter() >= maxLoginAttempts;
    }

    @Override
    public void resetAttemptManager() {
        ipLoginAttemptsByIpMap = new HashMap<>();
    }

    private void updateIpAttempts(IpLoginAttempt ipLoginAttempt, LogEntry logEntry) {
        if (ipLoginAttempt.getFirstAttempt().plusMinutes(attemptPeriodInMinutes).isAfter(logEntry.getEntryDateTime())) {
            ipLoginAttempt.setFailedAttemtpsCounter(ipLoginAttempt.getFailedAttemtpsCounter() + 1);
        }else{
            ipLoginAttempt.setFailedAttemtpsCounter(1);
            ipLoginAttempt.setFirstAttempt(logEntry.getEntryDateTime());
        }
    }


}
