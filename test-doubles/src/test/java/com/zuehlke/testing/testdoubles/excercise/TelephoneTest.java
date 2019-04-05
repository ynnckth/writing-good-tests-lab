package com.zuehlke.testing.testdoubles.excercise;

import com.zuehlke.testing.testdoubles.exercise.Telephone;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TelephoneTest {

	private static final String NUMBER = "0791234567";

	@Test
	public void testCall_busy_forwardToVoiceMail() {
		// arrange
		VoiceMailServiceSpy spy = new VoiceMailServiceSpy();
		Telephone testee = new Telephone(spy);
		testee.setBusy(true);

		// act
		testee.call(NUMBER);

		// assert
		assertThat(spy.getCallingNumber(), is(equalTo(NUMBER)));
	}
}
