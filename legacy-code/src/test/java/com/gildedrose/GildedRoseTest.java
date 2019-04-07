package com.gildedrose;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class GildedRoseTest {

    private static final int MAX_QUALITY = 50;

    private final String CONCERT_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private final String AGED_BRIE = "Aged Brie";
    private final String DEXTERITY_VEST = "+5 Dexterity Vest";
    private final String ELIXIR = "Elixir of the Mongoose";

    @Test
    void qualityAndSellInOfNormalItemsDecrease() {
        Item dexterityVest = new Item(DEXTERITY_VEST, 10, 20);
        Item elixir = new Item(ELIXIR, 5, 10);
        GildedRose app = new GildedRose(new Item[]{dexterityVest, elixir});

        app.updateQuality();

        assertAll(
                () -> assertThat(dexterityVest.quality, is(19)),
                () -> assertThat(dexterityVest.sellIn, is(9)),
                () -> assertThat(elixir.quality, is(9)),
                () -> assertThat(elixir.sellIn, is(4))
        );
    }

    @Test
    void onceSellInHasPassedQualityDegradesTwiceAsFast() {
        Item dexterityVest = new Item(DEXTERITY_VEST, 1, 20);
        Item elixir = new Item(ELIXIR, 1, 20);
        GildedRose app = new GildedRose(new Item[]{dexterityVest, elixir});

        letNDaysPass(app, 3);

        assertAll(
                () -> assertThat(dexterityVest.quality, is(15)),
                () -> assertThat(dexterityVest.sellIn, is(-2)),
                () -> assertThat(elixir.quality, is(15)),
                () -> assertThat(elixir.sellIn, is(-2))
        );
    }

    @Test
    void qualityOfAnItemIsNeverNegative() {
        Item dexterityVest = new Item(DEXTERITY_VEST, 5, 0);
        GildedRose app = new GildedRose(new Item[]{dexterityVest});

        app.updateQuality();

        assertAll(
                () -> assertThat(dexterityVest.quality, is(0)),
                () -> assertThat(dexterityVest.sellIn, is(4))
        );
    }

    @Test
    void qualityOfAnItemIsNeverGreaterThanMaxQuality() {
        Item agedBrie = new Item(AGED_BRIE, 5, MAX_QUALITY);
        GildedRose app = new GildedRose(new Item[]{agedBrie});

        app.updateQuality();

        assertAll(
                () -> assertThat(agedBrie.quality, is(MAX_QUALITY)),
                () -> assertThat(agedBrie.sellIn, is(4))
        );
    }

    @Test
    void legendaryItemsDontChange() {
        Item legendaryItem = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        GildedRose app = new GildedRose(new Item[]{legendaryItem});

        letNDaysPass(app, 3);

        assertAll(
                () -> assertThat(legendaryItem.quality, is(80)),
                () -> assertThat(legendaryItem.sellIn, is(0))
        );
    }

    @Test
    void qualityOfAgedBrieAndConcertPassesIncreasesOverTime() {
        Item agedBrie = new Item(AGED_BRIE, 5, 10);
        Item backstagePass = new Item(CONCERT_PASS, 15, 20);
        GildedRose app = new GildedRose(new Item[]{agedBrie, backstagePass});

        letNDaysPass(app, 3);

        assertAll(
                () -> assertThat(agedBrie.quality, is(13)),
                () -> assertThat(agedBrie.sellIn, is(2)),
                () -> assertThat(backstagePass.quality, is(23)),
                () -> assertThat(backstagePass.sellIn, is(12))
        );
    }

    @Test
    void qualityOfConcertPassesIncreasesBy2Between5And10Days() {
        Item backstagePass = new Item(CONCERT_PASS, 11, 20);
        GildedRose app = new GildedRose(new Item[]{backstagePass});

        letNDaysPass(app, 3);

        assertAll(
                () -> assertThat(backstagePass.quality, is(25)),
                () -> assertThat(backstagePass.sellIn, is(8))
        );
    }

    @Test
    void qualityOfConcertPassesIncreasesBy3Between0And5Days() {
        Item backstagePass = new Item(CONCERT_PASS, 6, 20);
        GildedRose app = new GildedRose(new Item[]{backstagePass});

        letNDaysPass(app, 3);

        assertAll(
                () -> assertThat(backstagePass.quality, is(28)),
                () -> assertThat(backstagePass.sellIn, is(3))
        );
    }

    @Test
    void qualityOfConcertPassesDecreasesTo0AfterDueDate() {
        Item backstagePass = new Item(CONCERT_PASS, 0, 20);
        GildedRose app = new GildedRose(new Item[]{backstagePass});

        app.updateQuality();

        assertAll(
                () -> assertThat(backstagePass.quality, is(0)),
                () -> assertThat(backstagePass.sellIn, is(-1))
        );
    }

    private void letNDaysPass(GildedRose app, int daysPassed) {
        IntStream.range(0, daysPassed).forEach(i -> app.updateQuality());
    }

}
