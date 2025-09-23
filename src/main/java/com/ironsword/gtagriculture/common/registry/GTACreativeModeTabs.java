package com.ironsword.gtagriculture.common.registry;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.common.data.GTAItemEntries;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class GTACreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> GTA_TAB = GTARegistries.REGISTRATE.defaultCreativeTab(
            GTAgriculture.MODID,
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator(GTAgriculture.MODID,GTARegistries.REGISTRATE))
                    .icon(GTAItems.MAX_TOMATO::asStack)
                    .title(Component.literal("GTAgriculture"))
                    .build()
    ).register();

    public static void init() {}
}
