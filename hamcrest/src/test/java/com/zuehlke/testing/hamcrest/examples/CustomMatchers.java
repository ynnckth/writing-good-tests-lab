package com.zuehlke.testing.hamcrest.examples;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import com.zuehlke.testing.hamcrest.Person;

//inspired by https://www.planetgeek.ch/2012/03/07/create-your-own-matcher/
public class CustomMatchers {

	public static Matcher<Person> hasAge(int i) {
		return new TypeSafeDiagnosingMatcher<Person>() {
			@Override
			public void describeTo(Description description) {
				description.appendText("getNumber should return ").appendValue(i);
			}

			@Override
			protected boolean matchesSafely(Person item, Description mismatchDescription) {
				mismatchDescription.appendText(" was ").appendValue(item.getAge());
				return i == item.getAge();
			}
		};
	}

	// FeatureMatcher is used to match one value (feature) of an object
	public static Matcher<Person> hasAgeFeatureMatcher(Integer i) {
		return new FeatureMatcher<Person, Integer>(equalTo(i), "age", "age") {
			@Override
			protected Integer featureValueOf(Person actual) {
				return actual.getAge();
			}
		};
	}

	public static Matcher<Person> hasFirstAndLastName(String firstName, String lastName) {
		return new TypeSafeDiagnosingMatcher<Person>() {
			@Override
			protected boolean matchesSafely(Person person, Description mismatchDescription) {
				mismatchDescription.appendText(" was ").appendValue(person.getFirstname() + " " + person.getLastname());
				return person.getFirstname().equals(firstName)
						&& person.getLastname().equals(lastName);
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("should return ").appendValue(firstName + " " + lastName);
			}
		};
	}
}
