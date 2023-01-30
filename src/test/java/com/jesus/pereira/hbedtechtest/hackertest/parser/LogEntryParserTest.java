package com.jesus.pereira.hbedtechtest.hackertest.parser;


import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.domain.model.LogEntry;
import com.jesus.pereira.hbedtechtest.hackertest.parser.impl.LogEntryParser;
import com.jesus.pereira.hbedtechtest.hackertest.utils.DateUtility;
import com.jesus.pereira.hbedtechtest.hackertest.validator.impl.LogEntryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class LogEntryParserTest {

    private String entry = "80.238.9.179,133612947,SIGNIN_FAILURE,Will.Smith";
    private LogEntry logEntry;

    @Mock
    LogEntryValidator validator;

    @InjectMocks
    LogEntryParser logEntryParser;

    @BeforeEach()
    void setup(){
        logEntry = LogEntry.builder()
                .ip("80.238.9.179")
                .entryDateTime(DateUtility.parseEpoch("133612947"))
                .loginAction(LoginActionEnum.SIGNIN_FAILURE)
                .userName("Will.Smith")
                .build();
    }

    @Test
    void shouldReturnParsedLogEntry(){
        LogEntry logEntryTest = logEntryParser.parse(entry);
        assertThat(logEntryTest).usingRecursiveComparison().isEqualTo(logEntry);
    }


}
