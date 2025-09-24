package com.ironsword.gtagriculture.common.registry;

import com.ironsword.gtagriculture.GTAgriculture;
import com.ironsword.gtagriculture.common.block.GrapeBlock;
import com.ironsword.gtagriculture.common.block.PepperBlock;
import com.ironsword.gtagriculture.common.data.GTAModels;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.ValueCheckCondition;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;

import java.util.stream.IntStream;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;
import static net.minecraft.data.models.model.TextureMapping.crop;
import static net.minecraft.data.models.model.TextureMapping.cross;

public class GTABlocks {
    static {
        REGISTRATE.creativeModeTab(()->GTACreativeModeTabs.GTA_TAB);
    }

    public static final BlockEntry<PepperBlock> CHILLY = REGISTRATE.block("chilly",PepperBlock::new)
            .initialProperties(()->Blocks.WHEAT)
            .lang("Pepper Seed")
            .blockstate(GTAModels.cropModel())
            .loot((table,block)->
                table.add(block,table.createCropDrops(
                        block,
                        GTAItems.CHILLY_PEPPER.asItem(),
                        block.asItem(),
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate
                                                    .Builder
                                                    .properties()
                                                    .hasProperty(CropBlock.AGE,7))))
            )
            .addLayer(() -> RenderType::cutout)
            .item(ItemNameBlockItem::new)
            .defaultModel()
            .lang("Pepper Seed")
            .build()
            .register();

    public static final BlockEntry<GrapeBlock> GRAPE_BLOCK = REGISTRATE.block("grape",GrapeBlock::new)
            .initialProperties(()->Blocks.WHEAT)
            .lang("Grape")
            //.blockstate(crop())
            .blockstate(GTAModels.cropModel())
            .loot((table,block)->
                table.add(block,table.createCropDrops(
                        block,
                        block.asItem(),
                        block.asItem(),
                        LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate
                                        .Builder
                                        .properties()
                                        .hasProperty(CropBlock.AGE,7))))
            )
            .addLayer(() -> RenderType::cutout)
            .item(ItemNameBlockItem::new)
            .defaultModel()
            .lang("Grape")
            .build()
            .register();


    public static void init(){
    }
}
