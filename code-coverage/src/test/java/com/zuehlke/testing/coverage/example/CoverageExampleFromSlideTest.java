package com.zuehlke.testing.coverage.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CoverageExampleFromSlideTest {

    CoverageExampleFromSlide testee = new CoverageExampleFromSlide();

    @Test
    void isShopOpenForVipDuringOpeningHours() {
        assertThat(testee.isShopOpen(LocalTime.of(13, 0), true), equalTo(true));
    }

    @Test
    void isShopOpenForVipOutsideOpeningHours() {
        assertThat(testee.isShopOpen(LocalTime.of(22, 0), true), equalTo(true));
    }

    @Test
    void isShopOpenDuringOpeningHours() {
        assertThat(testee.isShopOpen(LocalTime.of(14, 0), false), equalTo(true));
    }

    @Test
    void isShopClosedOutsideOpeningHours() {
        assertThat(testee.isShopOpen(LocalTime.of(23, 0), false), equalTo(false));
    }

    @ParameterizedTest
    @CsvSource({
            "7, false, false",
            "8, false, false",
            "9, false, true",
            "12, false, true",
            "18, false, true",
            "19, false, false",
            "20, false, false",
    })
    void isShopOpen(int hour, boolean isVip, boolean expectedOpen) {
        assertThat(testee.isShopOpen(LocalTime.of(hour, 0), isVip), equalTo(expectedOpen));
    }
}