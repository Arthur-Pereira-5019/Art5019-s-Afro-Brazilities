package com.art5019.afrobrazilities.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static com.art5019.afrobrazilities.block.Misc.*;
import static net.minecraft.world.item.Items.CAULDRON;
import static net.minecraft.world.item.Items.SAND;
import static net.minecraft.world.level.block.Blocks.HOPPER;

public class SoyFeedCauldron extends LayeredCauldronBlock{
    public SoyFeedCauldron(Biome.Precipitation precipitationType, CauldronInteraction.InteractionMap interactions, Properties properties) {
        super(precipitationType, interactions, properties);
    }

    @Override
    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if(blockState.getValue(LayeredCauldronBlock.LEVEL) == 3) {
            if(serverLevel.getBlockState(blockPos.below()).is(HOPPER)) {
                var container = HopperBlockEntity.
                        getContainerOrHandlerAt(serverLevel, blockPos.below(), Direction.UP);
                if(HopperBlockEntity.addItem(null, container.container(), new ItemStack(SOY_FEED_ITEM.asItem()), Direction.UP) == ItemStack.EMPTY) {
                    takeSoyFeed(serverLevel, blockPos);
                }
            }
        }
        serverLevel.scheduleTick(blockPos,this,1);
        super.tick(blockState, serverLevel, blockPos, randomSource);
    }

    private void takeSoyFeed(ServerLevel serverLevel, BlockPos blockPos) {
        serverLevel.setBlockAndUpdate(blockPos, SOY_OIL_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1));
        serverLevel.playLocalSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS,
        1,1, false);
    }

    @Override
    public void onPlace(BlockState p_51978_, Level p_51979_, BlockPos p_51980_, BlockState p_51981_, boolean p_51982_) {
        p_51979_.scheduleTick(p_51980_,this,1);
        super.onPlace(p_51978_, p_51979_, p_51980_, p_51981_, p_51982_);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(state.getValue(LayeredCauldronBlock.LEVEL) == 3 && level instanceof ServerLevel) {
            player.addItem(new ItemStack(SOY_FEED_ITEM.asItem()));
            takeSoyFeed((ServerLevel) level,pos);
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        return new ItemStack(CAULDRON);
    }
}
