package com.jesus.pereira.hbedtechtest.timecalculationtest;

import com.jesus.pereira.hbedtechtest.timecalculationtest.impl.TimeCalculatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TimeCalculatorTest {

    private String rfcDate1;
    private String rfcDate2;

    @InjectMocks
    TimeCalculatorImpl timeCalculator;

    @BeforeEach()
    void setUp(){
        rfcDate1 = "Thu, 21 Dec 2000 16:03:06 +0200";
        rfcDate2 = "Thu, 21 Dec 2000 16:01:07 +0200";
    }

    @Test
    void shouldReturnExactlyOneMinuteBetweeDates() {
        assertThat(timeCalculator.getMinutesBetweenDates(rfcDate1, rfcDate2)).isEqualTo(1);
    }

}
