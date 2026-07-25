package com.art5019.afrobrazilities.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import static com.art5019.afrobrazilities.block.Simple_Plants.ABSTRACT_BEAN_CODEC;

public class AbstractBeanCrop extends CropBlock {
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 6);

    public AbstractBeanCrop(Properties p_52247_) {
        super(p_52247_);
    }

    @Override
    public MapCodec<AbstractBeanCrop> codec() {
        return ABSTRACT_BEAN_CODEC.get();
    }

    @Override
    protected IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return 6;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }


}
