package com.jesus.pereira.hbedtechtest.hackertest.validator.impl;

import com.jesus.pereira.hbedtechtest.hackertest.domain.enums.LoginActionEnum;
import com.jesus.pereira.hbedtechtest.hackertest.exception.LogEntryParseException;
import com.jesus.pereira.hbedtechtest.hackertest.validator.IValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class LogEntryValidator implements IValidator<List<String>> {

    @Override
    public void validate(List<String> entries) {
        validateSize(entries);
        IntStream.range(0, entries.size()).forEach(index -> validateNullOrBlank(index, entries.get(index)));
        validateLoginAction(entries.get(2));
        validateUserName(entries.get(3));
    }

    private void validateUserName(String userName) {
        String regex = "^[A-Z]+[a-z]*\\.[A-Z]+[a-z]*$";
        if(!Pattern.matches(regex, userName)){
            throw new LogEntryParseException("Invalid username format", null);
        }
    }

    private void validateLoginAction(String loginAction) {
        if(Arrays.stream(LoginActionEnum.values())
                .noneMatch(loginActionEnum -> loginActionEnum.toString().equalsIgnoreCase(loginAction))) {
            throw new LogEntryParseException("Incorrect login action", null);
        }
    }

    private void validateSize(List<String> entries) {
        if (entries.size() != 4) {
            throw new LogEntryParseException("Entry has an incorrect number of parameters", null);
        }
    }

    private void validateNullOrBlank(int index, String param) {
        if (param.isBlank()) {
            throw new LogEntryParseException("No parameter with number %1$s exists", null);
        }
    }
}
