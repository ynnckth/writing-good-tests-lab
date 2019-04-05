package com.zuehlke.testing.testdata.examples;

import com.zuehlke.testing.testdata.PrimeFactor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PrimeFactorTest {

	@ParameterizedTest
	@ValueSource(ints = {2, 3, 5})
	void isPrime(int product) {
		assertThat(PrimeFactor.isPrime(product), is(true));
	}

	@DisplayName("Prime numbers using @CsvSource")
	@ParameterizedTest(name = "result for {0} should be {1}")
	@CsvSource({
			"1, false",
			"2, true",
			"3, true",
			"4, false",
			"5, true",
			"6, false",
	})
	void isPrimeUsingCsvSource(int number, boolean expectedPrime) {
		assertThat(PrimeFactor.isPrime(number), is(expectedPrime));
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 6, 9})
	void isNotPrime(int product) {
		assertThat(PrimeFactor.isPrime(product), is(false));
	}

	@DisplayName("Prime numbers using @MethodSource")
	@ParameterizedTest(name = "run #{index} with product {arguments}")
	@MethodSource("primeNumbers")
	void isPrimeFromMethodSource(int product) {
		assertThat(PrimeFactor.isPrime(product), is(true));
	}

	@ParameterizedTest
	@MethodSource("argumentsProvider")
	void primeFactors(int product, List<Integer> factors) {
		List<Integer> result = PrimeFactor.factorsOf(product);
		assertThat(result, is(equalTo(factors)));
	}


	private static Collection<Integer> primeNumbers() {
		return Arrays.asList(2, 3, 5, 7, 11, 13, 17);
	}

	private static Collection<Arguments> argumentsProvider() {
		return Arrays.asList(
				arguments( 1, Arrays.asList()), //
				arguments( 2, Arrays.asList(2)), //
				arguments( 3, Arrays.asList(3)), //
				arguments( 4, Arrays.asList(2, 2) ), //
				arguments( 5, Arrays.asList(5)), //
				arguments( 6, Arrays.asList(2, 3) ), //
				arguments( 7, Arrays.asList(7)), //
				arguments( 8, Arrays.asList(2, 2, 2) ), //
				arguments( 9, Arrays.asList(3, 3) ), //
				arguments( 10, Arrays.asList(2, 5) ), //
				arguments( 12, Arrays.asList(2, 2, 3) ), //
				arguments( 16, Arrays.asList(2, 2, 2, 2) ), //
				arguments( 18, Arrays.asList(2, 3, 3) ), //
				arguments( 26, Arrays.asList(2, 13) ), //
				arguments( 99, Arrays.asList(3, 3, 11) ), //
				arguments( 101, Arrays.asList(101)) //
		);
	}

}
