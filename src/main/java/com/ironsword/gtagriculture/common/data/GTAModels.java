package com.ironsword.gtagriculture.common.data;

import com.gregtechceu.gtceu.common.block.LampBlock;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

public class GTAModels {
    public static <T extends CropBlock> NonNullBiConsumer<DataGenContext<Block,T>, RegistrateBlockstateProvider> cropModel() {
        return (ctx,prov)->{
            prov.getVariantBuilder(ctx.getEntry())
                    .forAllStates(state -> {
                        String name = ctx.getName();
                        int age = state.getValue(CropBlock.AGE);
                        ModelBuilder<?> model = prov.models().crop("block/%s/stage_%d".formatted(name,age),prov.modLoc("block/%s/stage_%d".formatted(name,age)));

                        return ConfiguredModel.builder()
                                .modelFile(model)
                                .build();
                    });
        };
    }
}
