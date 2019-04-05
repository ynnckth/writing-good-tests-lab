package com.zuehlke.testing.mocking.exercises;

import com.zuehlke.testing.mocking.person.Person;
import com.zuehlke.testing.mocking.person.PersonDao;
import com.zuehlke.testing.mocking.person.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Excercises:
 *
 * Return Value: Write a Test that returns a valid person for a call to the
 * method findById(1). What happens if you call findById(2)?
 *
 * Exception: Write a Test that verifies valid ids. For negative ids an
 * IllegalArgumentException should be thrown.
 *
 * Method Called: Write a Test that verifies if a special method was called. The
 * desired result is a list with two persons.
 *
 * Call Count: Write a Test that verifies the number of calls for the code
 * snippet below for findById() and findAll() � check only for id 1 for exact
 * match.
 */
public class PersonServiceTest {

	@Mock
	private PersonDao personDaoMock;

	private PersonService personService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this); // alternatively with junit jupiter the MockitoExtension can be used
		personService = new PersonService(personDaoMock);
	}

	@Test
	void findById() {
		when(personDaoMock.findById(anyInt()))
				.thenReturn(new Person(11, "test", "ocino", 20));

		Person person = personService.findById(11);

		assertAll(
				() -> assertThat(person.getId(), equalTo(11)),
				() -> assertThat(person.getFirstname(), equalTo("test")),
				() -> assertThat(person.getLastname(), equalTo("ocino")),
				() -> assertThat(person.getAge(), equalTo(20)));
		verify(personDaoMock).findById(11);
	}

	@Test
	void findByInvalidNegativeId() {
		when(personDaoMock.findById(anyInt())).thenThrow(new IllegalArgumentException());
		assertThrows(IllegalArgumentException.class, () -> personService.findById(-1));
	}

	@Test
	void spyExample() {
		// a spy wraps a real object and provides spying functions for verification on top
		List<String> list = new ArrayList<>();
		List<String> spyList = Mockito.spy(list);

		spyList.add("one");
		spyList.add("two");
		spyList.add("three");

		assertThat(spyList, hasSize(3));
		verify(spyList).add("one");
		verify(spyList).add("two");
		verify(spyList).add("three");
	}
}
