package com.jesus.pereira.hbedtechtest.hackertest.attemptmanager;

import com.jesus.pereira.hbedtechtest.hackertest.attemptmanager.impl.AttemptManagerImpl;
import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class AttemptManagerTest {

    private LogEntry firstLogEntry;
    private LogEntry secondLogEntry;
    private LogEntry thirdLogEntry;
    private LogEntry fourthLogEntry;
    private LogEntry fifthLogEntry;

    @InjectMocks
    AttemptManagerImpl attemptManager;

    @BeforeEach()
    void setup(){
        firstLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();

        secondLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();

        thirdLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();

        fourthLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();

        fifthLogEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(LocalDateTime.now().minusMinutes(2))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();

        ReflectionTestUtils.setField(attemptManager, "attemptPeriodInMinutes", 5);
        ReflectionTestUtils.setField(attemptManager, "maxLoginAttempts", 5);
    }

    @AfterEach()
    void cleanUp(){
        attemptManager.resetAttemptManager();
    }

    @Test
    void shouldReturnFalseBecauseLoginIsCorrect(){
        firstLogEntry.setLoginAction(LoginActionEnum.SIGNIN_SUCCESS);
        assertThat(attemptManager.tooManyFailedAttempts(firstLogEntry)).isFalse();
    }

    @Test
    void shouldReturnFalseBecauseNotEnoughFalseAttempts(){
        assertThat(attemptManager.tooManyFailedAttempts(firstLogEntry)).isFalse();
    }

    @Test
    void shouldReturnTrueAfterFiveIncorrectAttemptsInTimeWindow(){
        assertThat(attemptManager.tooManyFailedAttempts(firstLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(secondLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(thirdLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(fourthLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(fifthLogEntry)).isTrue();
    }

    @Test
    void shouldReturnFalseBecauseFiveHavePassedBetweenFirstAndFifthAttempt(){
        fifthLogEntry.setEntryDateTime(LocalDateTime.now().plusMinutes(5));
        assertThat(attemptManager.tooManyFailedAttempts(firstLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(secondLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(thirdLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(fourthLogEntry)).isFalse();
        assertThat(attemptManager.tooManyFailedAttempts(fifthLogEntry)).isFalse();
    }


}
