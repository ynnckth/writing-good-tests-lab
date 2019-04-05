package com.zuehlke.testing.rules.exercises;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

class LoggingExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println(String.format("Class: %s, Method: %s", context.getTestClass(), context.getTestMethod()));
    }
}