package com.zuehlke.testing.junit5;

import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

// makes sense e.g. for integration tests where we want to define a max waiting duration
class AssertTimeoutTest {

    @Test
    void timeout() {
        // test execution will take as long as the timeout duration
        assertTimeout(ofMillis(1000), () -> {
            Thread.sleep(900); // the test will fail if this block takes longer than the specified duration
        });
    }

    @Test
    void timeoutFailFast() {
        // test execution will take as long as the inner block takes
        assertTimeoutPreemptively(ofMillis(1000), () -> {
            Thread.sleep(100); // the test will fail if this block takes longer than the specified duration
        });
    }
}