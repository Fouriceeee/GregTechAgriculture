package com.ironsword.gtagriculture.common.data;

import com.ironsword.gtagriculture.common.block.BerryBushBlock;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;

public class GTAModels {
    public static <T extends CropBlock> NonNullBiConsumer<DataGenContext<Block,T>, RegistrateBlockstateProvider> cropModel(String name) {
        return (ctx,prov)->
                prov.getVariantBuilder(ctx.getEntry())
                        .forAllStates(state -> {
                            int age = state.getValue(CropBlock.AGE);
                            ModelBuilder<?> model = prov.models().crop("block/%s/stage_%d".formatted(name,age),prov.modLoc("block/%s/stage_%d".formatted(name,age)));

                            return ConfiguredModel.builder()
                                .modelFile(model)
                                .build();
                    });
    }

    public static <T extends BerryBushBlock> NonNullBiConsumer<DataGenContext<Block,T>, RegistrateBlockstateProvider> tberryBushModel(String name) {
        return (ctx,prov)->
                prov.getVariantBuilder(ctx.getEntry())
                        .forAllStates(state -> {
                            int age = state.getValue(BerryBushBlock.AGE);
                            Direction direction = state.getValue(BerryBushBlock.FACING);
                            return ConfiguredModel.builder().modelFile(new ModelFile.UncheckedModelFile(prov.modLoc("block/%s/stage_%d/%s".formatted(name,age,direction.getName())))).build();
                        });
    }

    public static <T extends BerryBushBlock> NonNullBiConsumer<DataGenContext<Block,T>, RegistrateBlockstateProvider> berryBushModel(String name) {
        return (ctx,prov)->
                prov.getVariantBuilder(ctx.getEntry())
                        .forAllStates(state -> {
                            int age = state.getValue(BerryBushBlock.AGE);
                            Direction direction = state.getValue(BerryBushBlock.FACING);

                            ModelFile parent = prov.models().getExistingFile(prov.modLoc("block/berry_bush/stage_%d/%s".formatted(age,direction.getName())));
                            ModelBuilder<?> model = prov.models().getBuilder("block/%s/stage_%d/%s".formatted(ctx.getName(),age,direction.getName())).parent(parent);
                            model.texture("stage_%d".formatted(age),"block/%s/stage_%d".formatted(name,age));

                            return ConfiguredModel.builder().modelFile(model).build();
                        });
    }

}
