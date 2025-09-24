package com.ironsword.gtagriculture.common.registry;

import com.ironsword.gtagriculture.common.block.GTACropBlock;
import com.ironsword.gtagriculture.common.data.GTAModels;
import com.ironsword.gtagriculture.common.data.GTAShapes;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.phys.shapes.VoxelShape;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;

public class GTABlocks {
    static {
        REGISTRATE.creativeModeTab(()->GTACreativeModeTabs.GTA_TAB);
    }

    public static final BlockEntry<GTACropBlock> CHILLY_PEPPER_SEED = REGISTRATE.block("chilly_pepper_seed",p-> new GTACropBlock(p,GTAShapes.CROP))
            .initialProperties(()->Blocks.WHEAT)
            .lang("Chilly Pepper")
            .blockstate(GTAModels.cropModel("chilly_pepper"))
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
            .lang("Chilly Pepper Seed")
            .build()
            .register();

    public static final BlockEntry<GTACropBlock> GRAPE_SEED = REGISTRATE.block("grape_seed",p->new GTACropBlock(p,GTAShapes.CROP))
            .initialProperties(()->Blocks.WHEAT)
            .lang("Grape")
            .blockstate(GTAModels.cropModel("grape"))
            .loot((table,block)->
                    table.add(block,table.createCropDrops(
                            block,
                            GTAItems.GRAPE.asItem(),
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
            .lang("Grape Seed")
            .build()
            .register();

    public static final BlockEntry<GTACropBlock> RAPE_SEED = REGISTRATE.block("rape_seed",p-> new GTACropBlock(p,GTAShapes.CROP))
            .initialProperties(()->Blocks.WHEAT)
            .lang("Rape")
            .blockstate(GTAModels.cropModel("rape"))
            .loot((table,block)->
                    table.add(block,table.createCropDrops(
                            block,
                            GTAItems.RAPE.asItem(),
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
            .lang("Rape Seed")
            .build()
            .register();

    //不知道为啥，这样写就是不行
    /*
    private BlockEntry<GTACropBlock> cropWithSeed(String blockId, String blockName, Item cropItem, String seedName, String texture, VoxelShape[] shape) {
        return REGISTRATE.block(blockId,p-> new GTACropBlock(p,shape))
                .initialProperties(()-> Blocks.WHEAT)
                .lang(blockName)
                .blockstate(GTAModels.cropModel(texture))
                .loot((table,block)->
                        table.add(block,table.createCropDrops(
                                block,
                                cropItem,
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
                .lang(seedName)
                .build()
                .register();
    }
    */

    public static void init(){
    }
}
