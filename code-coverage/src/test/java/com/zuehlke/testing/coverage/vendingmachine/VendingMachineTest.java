package com.zuehlke.testing.coverage.vendingmachine;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class VendingMachineTest {

    @ParameterizedTest(name = "{0} bottles should cost CHF {1}")
    @CsvSource({
            "0, 0",
            "1, 5",
            "2, 10",
            "3, 15",
            "4, 20",
    })
    void calculateChangeWithoutPriceReduction(int amountOfBottles, int expectedPrice) {
        VendingMachine vendingMachine = new VendingMachine(Integer.MAX_VALUE, 5, Integer.MAX_VALUE);
        assertThat(vendingMachine.calculatePrice(amountOfBottles), equalTo(expectedPrice));
    }

    @ParameterizedTest(name = "{0} bottles and each {1} for free should cost CHF {2}")
    @CsvSource({
            "0, 3, 0",
            "1, 3, 5",
            "2, 3, 10",
            "3, 3, 10",
            "4, 3, 15",
            "5, 3, 20",
            "6, 3, 20",
    })
    void calculateWithPriceReduction(int amountOfBottles, int eachXthBottleForFree, int expectedPrice) {
        VendingMachine vendingMachine = new VendingMachine(Integer.MAX_VALUE, 5, eachXthBottleForFree);
        assertThat(vendingMachine.calculatePrice(amountOfBottles), equalTo(expectedPrice));
    }

    @ParameterizedTest
    @CsvSource({
          "1, 5, 0",
          "2, 10, 0",
          "3, 15, 0"
    })
    void calculateChange(int amountOfBottles, int moneyEntered, int expectedChange)
            throws NotEnoughMoneyException, NoMoreChangeException {
        VendingMachine vendingMachine = new VendingMachine(Integer.MAX_VALUE, 5, 5);
        assertThat(vendingMachine.calculateChange(amountOfBottles, moneyEntered), equalTo(expectedChange));
    }

}