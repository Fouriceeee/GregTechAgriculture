package com.ironsword.gtagriculture.common.data;

import com.ironsword.gtagriculture.Utils;
import com.ironsword.gtagriculture.common.block.BerryBushBlock;
import com.ironsword.gtagriculture.common.block.GTACropBlock;
import com.ironsword.gtagriculture.common.registry.GTACreativeModeTabs;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

import static com.ironsword.gtagriculture.common.registry.GTARegistries.REGISTRATE;

public class GTABlocks {
    static {
        REGISTRATE.creativeModeTab(()-> GTACreativeModeTabs.GTA_TAB);
    }

    public static final BlockEntry<GTACropBlock> CHILL_PEPPER_SEED = cropWithSeed("chilly_pepper");
    public static final BlockEntry<GTACropBlock> GRAPE_SEED = cropWithSeed("grape");
    public static final BlockEntry<GTACropBlock> RAPE_SEED = cropWithSeed("rape");
    public static final BlockEntry<GTACropBlock> TOMATO_SEED = cropWithSeed("tomato");

    public static final BlockEntry<BerryBushBlock> TEST_BERRY_BUSH = REGISTRATE.block("test_berry_bush",BerryBushBlock::new)
            .initialProperties(()->Blocks.SWEET_BERRY_BUSH)
            .blockstate(GTAModels.tberryBushModel("test_berry_bush"))
            .addLayer(() -> RenderType::cutout)
            .lang("Test Berry Bush")
            .onRegisterAfter(ForgeRegistries.ITEMS.getRegistryKey(), block->block.attachBerryItem(GTAItems.TEST_BERRY.get()))
            .item(BlockItem::new)
            .model((ctx,prov)->
                            prov.withExistingParent("item/test_berry_bush",prov.modLoc("block/test_berry_bush/stage_0/down")))
            .build()
            .register();

    //helper
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
