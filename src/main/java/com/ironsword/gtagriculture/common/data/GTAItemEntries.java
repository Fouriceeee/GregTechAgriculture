package com.ironsword.gtagriculture.common.data;

import com.ironsword.gtagriculture.api.GTAItemEntry;

import java.util.ArrayList;
import java.util.List;

public class GTAItemEntries {
    public static List<GTAItemEntry> cropList = new ArrayList<>();

    public static GTAItemEntry
            CHILLY_PEPPER = GTAItemEntry.create("chilli_pepper").foodProperties(1,0.3f).tooltip("It is red and hot").toList(cropList),
            LEMON = GTAItemEntry.create("lemon").foodProperties(1,03f).tooltip("Don't make Lemonade").toList(cropList),
            TOMATO = GTAItemEntry.create("tomato").foodProperties(1,0.2f).tooltip("Solid Ketchup").toList(cropList),
            MAX_TOMATO = GTAItemEntry.create("max_tomato").foodProperties(9,1.0f).tooltip("Full Health in one Tomato").toList(cropList),
            GRAPE = GTAItemEntry.create("grape").foodProperties(2,0.3f).tooltip("Source of Wine").toList(cropList),
            ONION = GTAItemEntry.create("onion").foodProperties(2,0.2f).tooltip("Taking over the whole Taste").toList(cropList),
            CUCUMBER = GTAItemEntry.create("cucumber").foodProperties(1,0.2f).tooltip("Not a Sea Cucumber!").toList(cropList),
            RAPE = GTAItemEntry.create("rape").tooltip("Time to oil up!").toList(cropList);

    public static void init() {}
}
