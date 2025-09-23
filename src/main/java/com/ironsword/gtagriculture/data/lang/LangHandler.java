package com.ironsword.gtagriculture.data.lang;

import com.gregtechceu.gtceu.data.lang.ItemLang;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.common.registry.GTAItems;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import com.tterrag.registrate.util.entry.ItemEntry;

public class LangHandler extends com.gregtechceu.gtceu.data.lang.LangHandler {
    public static void init(RegistrateLangProvider provider) {
        initItemTooltips(provider);
    }

    private static void initItemTooltips(RegistrateLangProvider provider) {
        provider.add(tooltipKey(GTAItems.CHILLY_PEPPER),"§7It is red and hot");
        provider.add(tooltipKey(GTAItems.LEMON),"§7Don't make Lemonade");
        provider.add(tooltipKey(GTAItems.TOMATO),"§7Solid Ketchup");
        provider.add(tooltipKey(GTAItems.MAX_TOMATO),"§7Full Health in one Tomato");
        provider.add(tooltipKey(GTAItems.GRAPE),"§7Source of Wine");
        provider.add(tooltipKey(GTAItems.ONION),"§7Taking over the whole Taste");
        provider.add(tooltipKey(GTAItems.CUCUMBER),"§7Not a Sea Cucumber!");
        provider.add(tooltipKey(GTAItems.RAPE),"§7Time to oil up!");
    }
    private static String tooltipKey(ItemEntry entry) {
        return "item." + entry.getKey().location().toLanguageKey() + ".tooltip";
    }
}
