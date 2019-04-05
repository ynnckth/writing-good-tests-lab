package com.zuehlke.testing.testdoubles.excercise;

import com.zuehlke.testing.testdoubles.exercise.VoiceMailService;

public class VoiceMailServiceSpy implements VoiceMailService {

    private String callingNumber;

    @Override
    public void call(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    String getCallingNumber() {
        return callingNumber;
    }
}
