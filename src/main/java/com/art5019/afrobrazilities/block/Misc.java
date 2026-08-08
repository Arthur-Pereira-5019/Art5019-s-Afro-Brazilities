package com.art5019.afrobrazilities.block;

import com.art5019.afrobrazilities.Art5019sAfrobrazilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Map;
import java.util.function.Predicate;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.BLOCKS;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static net.minecraft.core.cauldron.CauldronInteraction.fillBucket;
import static net.minecraft.core.cauldron.CauldronInteraction.newInteractionMap;
import static net.minecraft.world.level.block.Blocks.CAULDRON;

@Mod(MODID)
public class Misc {
    public static final DeferredBlock<Block> SOY_OIL_CAULDRON = BLOCKS.register("soy_oil_cauldron", (x) -> new LayeredCauldronBlock(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, cauldronProperties(x)));
    public static final DeferredBlock<Block> SOY_FEED_CAULDRON = BLOCKS.register("soy_feed_cauldron", (x) -> new SoyFeedCauldron(Biome.Precipitation.NONE, SoyFeedInteractions(), cauldronProperties(x)));


    private static BlockBehaviour.Properties cauldronProperties(ResourceLocation name) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).
                requiresCorrectToolForDrops().strength(2.0F).noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, name));

    }

    private static CauldronInteraction.InteractionMap SoyFeedInteractions() {
        Map<Item, CauldronInteraction> map1 = newInteractionMap("water").map();
        CauldronInteraction.InteractionMap map = new CauldronInteraction.InteractionMap("soy_feed",map1);
        return map;
    }

    private static InteractionResult fillBucketSoyFeed(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack emptyStack, ItemStack filledStack, Predicate<BlockState> statePredicate) {
        if (!statePredicate.test(state)) {
            return InteractionResult.TRY_WITH_EMPTY_HAND;
        } else {
            if (!level.isClientSide) {
                Item item = emptyStack.getItem();
                player.setItemInHand(hand, ItemUtils.createFilledResult(emptyStack, player, filledStack));
                player.awardStat(Stats.USE_CAULDRON);
                player.awardStat(Stats.ITEM_USED.get(item));
                level.setBlockAndUpdate(pos, SOY_OIL_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL,1));
                level.playSound((Entity)null, pos, SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }
}
