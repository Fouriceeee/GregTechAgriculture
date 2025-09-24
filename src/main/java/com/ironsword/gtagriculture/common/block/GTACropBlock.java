package com.ironsword.gtagriculture.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GTACropBlock extends CropBlock {
    private final VoxelShape[] SHAPE_BY_AGE;

    public GTACropBlock(Properties properties, VoxelShape[] shape) {
        super(properties);
        SHAPE_BY_AGE = shape;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return this.asItem();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }
}
