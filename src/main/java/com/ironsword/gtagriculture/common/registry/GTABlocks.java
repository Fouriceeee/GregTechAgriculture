package com.ironsword.gtagriculture.common.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTBlockBuilder;
import com.ironsword.gtagriculture.Utils;
import com.ironsword.gtagriculture.common.block.GTACropBlock;
import com.ironsword.gtagriculture.common.data.GTAModels;
import com.ironsword.gtagriculture.common.data.GTAShapes;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;

public class GTABlocks {
    static {
        REGISTRATE.creativeModeTab(()->GTACreativeModeTabs.GTA_TAB);
    }

    public static final BlockEntry<GTACropBlock> CHILL_PEPPER_SEED = cropWithSeed("chilly_pepper");
    public static final BlockEntry<GTACropBlock> GRAPE_SEED = cropWithSeed("grape");
    public static final BlockEntry<GTACropBlock> RAPE_SEED = cropWithSeed("rape");
    public static final BlockEntry<GTACropBlock> TOMATO_SEED = cropWithSeed("tomato");

    private static BlockEntry<GTACropBlock> cropWithSeed(String cropName){
        String id = cropName + "_seed";
        return cropWithSeed(id, Utils.id2Name(cropName),Utils.id2Name(id),cropName);
    }

    private static BlockEntry<GTACropBlock> cropWithSeed(String blockId, String blockName, String itemName, String texture) {
        return cropWithSeed(blockId,blockName,itemName,texture,GTAShapes.CROP);
    }

    private static BlockEntry<GTACropBlock> cropWithSeed(String blockId, String blockName, String itemName, String texture, VoxelShape[] shape) {
        return REGISTRATE.block(blockId,p-> new GTACropBlock(p,shape))
                .initialProperties(()-> Blocks.WHEAT)
                .lang(blockName)
                .blockstate(GTAModels.cropModel(texture))
                .addLayer(() -> RenderType::cutout)
                .item(ItemNameBlockItem::new)
                .defaultModel()
                .lang(itemName)
                .build()
                .register();
    }

    private static BlockEntry<GTACropBlock> test(String blockId, String blockName, String itemName,String texture,VoxelShape[] shape, Item cropItem ) {
        return REGISTRATE.block(blockId,p-> new GTACropBlock(p,shape))
                .initialProperties(()-> Blocks.WHEAT)
                .lang(blockName)
                .blockstate(GTAModels.cropModel(texture))
                .addLayer(() -> RenderType::cutout)
                .item(ItemNameBlockItem::new)
                .defaultModel()
                .lang(itemName)
                .build()
                .onRegisterAfter(ForgeRegistries.Keys.ITEMS,block->{
                })

                .register();
    }

    public static void init(){
    }
}
