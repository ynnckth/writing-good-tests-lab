package com.zuehlke.testing.hamcrest;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static com.zuehlke.testing.hamcrest.examples.CustomMatchers.hasFirstAndLastName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PersonTest {

    @Test
    void constructor() {
        Person dave = new Person(1, "Dave", "Daggeler", 15);

        assertThat(dave.getId(), is(equalTo(1)));
        assertThat(dave, hasFirstAndLastName("Dave", "Daggeler"));
    }

}