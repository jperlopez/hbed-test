package com.jesus.pereira.hbedtechtest.hackertest.detector;

import com.jesus.pereira.hbedtechtest.hackertest.attemptmanager.IAttemptManager;
import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import com.jesus.pereira.hbedtechtest.hackertest.detector.impl.HackerDetectorImpl;
import com.jesus.pereira.hbedtechtest.hackertest.parser.IParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class HackerDetectorTest {

    private LogEntry firstLogEntry;
    private String line;

    @Mock
    IAttemptManager attemptManager;

    @Mock
    IParser<LogEntry> parser;

    @InjectMocks
    HackerDetectorImpl hackerDetector;

    @BeforeEach()
    void setup() {
        firstLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();
        line = "Test";
    }

    @Test
    void shouldReturnIpBecauseIpHasFiveOrMoreFailedLoginAttempts(){
        given(parser.parse(line)).willReturn(firstLogEntry);
        given(attemptManager.tooManyFailedAttempts(firstLogEntry)).willReturn(true);

        assertThat(hackerDetector.parseLine(line)).isEqualTo("80.238.9.179");
        verify(parser, times(1)).parse(any());
        verify(attemptManager, times(1)).tooManyFailedAttempts(any());
    }

    @Test
    void shouldReturnNullBecauseLoginWasCorrect(){
        given(parser.parse(line)).willReturn(firstLogEntry);
        given(attemptManager.tooManyFailedAttempts(firstLogEntry)).willReturn(false);

        assertThat(hackerDetector.parseLine(line)).isEqualTo(null);
        verify(parser, times(1)).parse(any());
        verify(attemptManager, times(1)).tooManyFailedAttempts(any());
    }


}
