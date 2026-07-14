package com.art5019.afrobrazilities.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.art5019.afrobrazilities.block.Simple_Plants.GROWN_DANDELION;
import static net.minecraft.world.level.block.Blocks.DANDELION;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
        @Inject(method = "randomTick", at = @At("HEAD"))
        private void onRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
            int r = random.nextInt(0,40);
            if(r == 17) {
                if(level.getRawBrightness(pos, 0) >= 8 && state.is(DANDELION)) {
                    level.setBlock(pos, GROWN_DANDELION.get().defaultBlockState(), FlowerBlock.UPDATE_ALL);
                }
            }
        }
}
