package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    private final int MAX_QUALITY = 50;

    private final String AGED_BRIE = "Aged Brie";
    private final String CONCERT_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals(AGED_BRIE)
                    && !item.name.equals(CONCERT_BACKSTAGE_PASSES)) {
                if (item.quality > 0) {
                    if (!item.name.equals(SULFURAS)) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < MAX_QUALITY) {
                    item.quality = item.quality + 1;

                    if (item.name.equals(CONCERT_BACKSTAGE_PASSES)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals(SULFURAS)) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals(AGED_BRIE)) {
                    if (!item.name.equals(CONCERT_BACKSTAGE_PASSES)) {
                        if (item.quality > 0) {
                            if (!item.name.equals(SULFURAS)) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}