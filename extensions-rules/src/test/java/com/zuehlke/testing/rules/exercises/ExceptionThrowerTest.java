package com.zuehlke.testing.rules.exercises;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(LoggingExtension.class)
class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void throwRuntimeExceptionIfNegative() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> exceptionThrower.throwRuntimeException(-1));
        assertThat(exception.getMessage(), equalTo("Illegal argument: parameter must be <= 0"));
    }

    @Test
    void throwRuntimeExceptionIfPositiveOr0() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> exceptionThrower.throwRuntimeException(1));
        assertThat(exception.getMessage(), equalTo("Runtime exception occurred"));
    }

    @Test
    void throwRuntimeExceptionWithCause() {
        RuntimeException exception = assertThrows(RuntimeException.class, exceptionThrower::throwExceptionWithCause);
        assertThat(exception.getCause(), isA(NullPointerException.class));
        assertThat(exception.getCause().getMessage(), equalTo("Oops! Something wasn't supposed to be null here."));
    }

}