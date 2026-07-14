package com.art5019.afrobrazilities.block;

import com.art5019.afrobrazilities.events.WorldTick;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import static com.art5019.afrobrazilities.block.Simple_Plants.*;
import static com.art5019.afrobrazilities.particles.ParticleTypes.DANDELION_SEED;
import static net.minecraft.world.level.block.Blocks.AIR;
import static net.minecraft.world.level.block.Blocks.DANDELION;

public class GrownDandelion extends FlowerBlock {
    public GrownDandelion(SuspiciousStewEffects suspiciousStewEffects, BlockBehaviour.Properties properties) {
        super(suspiciousStewEffects, properties);
    }

    @Override
    public MapCodec<GrownDandelion> codec() {
        return GROWN_DANDELION_CODEC.get();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int r = random.nextInt(0,30);
        if(r == 7) {
            level.setBlock(pos, AIR.defaultBlockState(), FlowerBlock.UPDATE_ALL);
            spread(level, pos, random);
        }
    }

    @Override
    protected void onExplosionHit(BlockState blockState, ServerLevel level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> consumer) {
        if (explosion.canTriggerBlocks()) {
            spread(level,pos,level.random);
            level.setBlock(pos, AIR.defaultBlockState(), FlowerBlock.UPDATE_ALL);
        }
        super.onExplosionHit(blockState, level, pos, explosion, consumer);
    }

    public static void spread(ServerLevel sl, BlockPos b, RandomSource r) {
        sl.sendParticles(
                DANDELION_SEED.get(),
                b.getX(),
                b.getY() + 1,
                b.getZ(),
                140,
                0.7,
                0.7,
                0.7,
                1
        );
        AtomicInteger placed = new AtomicInteger(0);
        for (int t = 0; t < 200; t++) {
            WorldTick.queueServerWork(t * 2, () -> {
                if(placed.get() < 5) {
                    BlockPos nb = new BlockPos(b.getX() + r.nextInt(-13, 13), b.getY() + r.nextInt(-8, 2), b.getZ() + r.nextInt(-13, 13));
                    if (sl.getBlockState(nb).is(BlockTags.DIRT)) {
                        if (sl.getBlockState(nb.offset(0, 1, 0)).is(BlockTags.AIR)) {
                            sl.setBlock(nb.offset(0, 1, 0), DANDELION_SPROUT.get().defaultBlockState(), Block.UPDATE_ALL);
                            placed.addAndGet(1);
                        }
                    }
                }
            });
        }
    }
}


