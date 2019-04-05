import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorClass {

    @Test
    void addEmpty() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.sum("");
        assertEquals(0, sum);
    }

    @Test
    void addOneNumber() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.sum("5");
        assertEquals(5, sum);
    }

    @Test
    void addTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.sum("5,5");
        assertEquals(10, sum);
    }

    @ParameterizedTest(name = "{0} should be {1}")
    @CsvSource({
            "'1,1', 2",
            "'1,2', 3",
            "'10,10', 20",
    })
    void addTwoNumbers(String input, int expectedSum) {
        StringCalculator calculator = new StringCalculator();
        assertEquals(expectedSum, calculator.sum(input));
    }

    @Test
    void addThreeNumbers() {
        StringCalculator calculator = new StringCalculator();
        int sum = calculator.sum("5,5,5");
        assertEquals(15, sum);
    }

    @ParameterizedTest(name = "{0} should be {1}")
    @CsvSource({
            "'1,1,1', 3",
            "'1,2,3', 6",
            "'10,10,10', 30",
    })
    void addThreeNumbers(String input, int expectedSum) {
        StringCalculator calculator = new StringCalculator();
        assertEquals(expectedSum, calculator.sum(input));
    }

    @Test
    void addMoreThanThreeNumbersThrowsException() {
        StringCalculator calculator = new StringCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.sum("5,5,5,5"));
    }


    private class StringCalculator {

        int sum(String input) {
            if (input.isEmpty()) {
                return 0;
            }

            String[] operands = input.split(",");

            if (operands.length > 3) {
                throw new IllegalArgumentException();
            }

            return Arrays.stream(operands)
                    .map(Integer::valueOf)
                    .reduce((a, b) -> a + b)
                    .get();
        }
    }
}
