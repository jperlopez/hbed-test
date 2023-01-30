package com.jesus.pereira.hbedtechtest.hackertest.validator;

import com.jesus.pereira.hbedtechtest.hackertest.exception.LogEntryParseException;
import com.jesus.pereira.hbedtechtest.hackertest.validator.impl.LogEntryValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LogEntryValidatorTest {

    private List<String> entries = new ArrayList<>();

    @InjectMocks
    LogEntryValidator logEntryValidator;

    @BeforeEach()
    void setUp(){
        entries.add("80.238.9.179");
        entries.add("1336129471");
        entries.add("SIGNIN_SUCCESS");
        entries.add("Will.Smith");
    }

    @Test
    void shouldNotThrowAnyExceptionBecauseLineIsCorrect(){
        logEntryValidator.validate(entries);
    }

    @Test
    void shouldThrowExceptionBecauseLineHasNotEnoughParams(){
        entries.remove(0);
        assertThatThrownBy(() -> logEntryValidator.validate(entries))
                .isInstanceOf(LogEntryParseException.class);
    }

    @Test
    void shouldThrowExceptionBecauseLoginActionDoesNotExists(){
        String wrongLoginAction = "Error";
        entries.set(2,wrongLoginAction);
        assertThatThrownBy(() -> logEntryValidator.validate(entries))
                .isInstanceOf(LogEntryParseException.class);
    }

    @Test
    void shouldThrowExceptionBecauseUseNameFormatIsIncorrect(){
        String name = "Will Smith";
        entries.set(3,name);
        assertThatThrownBy(() -> logEntryValidator.validate(entries))
                .isInstanceOf(LogEntryParseException.class);
    }
}
