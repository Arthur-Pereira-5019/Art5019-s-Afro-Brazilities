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
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.function.Predicate;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;
import static net.minecraft.core.cauldron.CauldronInteraction.fillBucket;
import static net.minecraft.core.cauldron.CauldronInteraction.newInteractionMap;
import static net.minecraft.world.level.block.Blocks.CAULDRON;

@Mod(MODID)
public class Misc {
    public static final DeferredBlock<Block> SOY_FEED = BLOCKS.register("soy_feed", (x) -> new Block(soyFeedProperties(x)));
    public static final DeferredBlock<Block> SOY_OIL_CAULDRON = BLOCKS.register("soy_oil_cauldron", (x) -> new LayeredCauldronBlock(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, cauldronProperties(x)));
    public static final DeferredBlock<Block> SOY_FEED_CAULDRON = BLOCKS.register("soy_feed_cauldron", (x) -> new SoyFeedCauldron(Biome.Precipitation.NONE, SoyFeedInteractions(), cauldronProperties(x)));

    public static final DeferredItem<BlockItem> SOY_FEED_ITEM = ITEMS.registerSimpleBlockItem(
            "soy_feed",
            SOY_FEED,
            new Item.Properties()
    );

    private static BlockBehaviour.Properties cauldronProperties(ResourceLocation name) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).
                requiresCorrectToolForDrops().strength(2.0F).noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, name));

    }

    private static BlockBehaviour.Properties soyFeedProperties(ResourceLocation name) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW)
                .instrument(NoteBlockInstrument.BANJO).
                strength(0.5F).
                sound(SoundType.MOSS)
                .setId(ResourceKey.create(Registries.BLOCK, name));

    }

    private static CauldronInteraction.InteractionMap SoyFeedInteractions() {
        Map<Item, CauldronInteraction> map1 = newInteractionMap("water").map();
        CauldronInteraction.InteractionMap map = new CauldronInteraction.InteractionMap("soy_feed",map1);
        return map;
    }
}
