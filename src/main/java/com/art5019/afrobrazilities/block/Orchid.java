package com.art5019.afrobrazilities.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TriState;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.ToIntFunction;

import static com.art5019.afrobrazilities.block.Simple_Plants.*;

public class Orchid extends FlowerBlock {
    private final ItemLike dye;
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;

    public Orchid(SuspiciousStewEffects suspiciousStewEffects, BlockBehaviour.Properties properties, ItemLike dye) {
        super(suspiciousStewEffects, properties);
        this.dye = dye;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.LOGS);
    }

    @Override
    protected boolean canSurvive(BlockState p_401395_, LevelReader p_401031_, BlockPos p_401248_) {
        BlockPos blockpos = p_401248_.below();
        BlockState belowBlockState = p_401031_.getBlockState(blockpos);
        return belowBlockState.is(BlockTags.LOGS);
    }

    public ItemLike getDye() {
        return dye;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153687_) {
        p_153687_.add(new Property[]{LEVEL});
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();
        int level = 0;
        if(state.is(RAINBOW_ORCHID)) {
            level = 4;
        } else if (state.is(STARRY_ORCHID)) {
            level = 5;
        }
        return this.defaultBlockState().setValue(LEVEL, level);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.is(RAINBOW_ORCHID)) {
            level.sendParticles(new DustParticleOptions(255, 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(255255, 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(16711680, 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(ARGB.color(128, 0, 128), 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(ARGB.color(255, 0, 255), 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(ARGB.color(255, 255, 0), 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
            level.sendParticles(new DustParticleOptions(ARGB.color(255, 153, 0), 1),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 20, 0.3, 0.3, 0.3, 1
            );
        } else if (state.is(STARRY_ORCHID)) {
            level.sendParticles(
                    ParticleTypes.GLOW_SQUID_INK,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    60,
                    0.3,
                    0.3,
                    0.3,
                    1
            );
        }
        super.randomTick(state, level, pos, random);
    }


    public static ToIntFunction<BlockState> LIGHT_EMISSION = (x) -> (Integer)x.getValue(LEVEL);;
}
