package com.art5019.afrobrazilities.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

import static com.art5019.afrobrazilities.block.Simple_Plants.DANDELION_SPROUT_CODEC;
import static com.art5019.afrobrazilities.block.Simple_Plants.GROWN_DANDELION;
import static net.minecraft.world.level.block.Blocks.DANDELION;

public class DandelionSprout extends FlowerBlock {
    public DandelionSprout(SuspiciousStewEffects suspiciousStewEffects, Properties properties) {
        super(suspiciousStewEffects, properties);
    }

    @Override
    public MapCodec<DandelionSprout> codec() {
        return DANDELION_SPROUT_CODEC.get();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(level.getRawBrightness(pos, 0) >= 8) {
            level.setBlock(pos, DANDELION.defaultBlockState(), FlowerBlock.UPDATE_ALL);
        }
    }
}
