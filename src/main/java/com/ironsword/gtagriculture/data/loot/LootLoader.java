package com.ironsword.gtagriculture.data.loot;

import com.ironsword.gtagriculture.common.block.GTACropBlock;
import com.ironsword.gtagriculture.common.data.GTABlocks;
import com.ironsword.gtagriculture.common.data.GTAItems;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.providers.loot.RegistrateLootTableProvider;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;

public class LootLoader {
    public static void init(RegistrateLootTableProvider prov) {
        addCropLootTable(prov,GTABlocks.CHILL_PEPPER_SEED.get(),GTAItems.CHILLY_PEPPER.asItem());
        addCropLootTable(prov,GTABlocks.GRAPE_SEED.get(), GTAItems.GRAPE.asItem());
        addCropLootTable(prov,GTABlocks.RAPE_SEED.get(), GTAItems.RAPE.asItem());
        addCropLootTable(prov,GTABlocks.TOMATO_SEED.get(),GTAItems.TOMATO.asItem());
    }

    private static void addCropLootTable(RegistrateLootTableProvider prov,GTACropBlock seedBlock, Item cropItem){
        prov.addLootAction(RegistrateLootTableProvider.LootType.BLOCK,cropLootTable(seedBlock, cropItem));
    }

    private static NonNullConsumer<RegistrateBlockLootTables> cropLootTable(GTACropBlock seedBlock, Item cropItem){
        return table ->
                table.add(seedBlock,table.createCropDrops(
                        seedBlock,
                        cropItem,
                        seedBlock.asItem(),
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(seedBlock)
                                .setProperties(StatePropertiesPredicate
                                        .Builder
                                        .properties()
                                        .hasProperty(CropBlock.AGE,7))));
    }
}
