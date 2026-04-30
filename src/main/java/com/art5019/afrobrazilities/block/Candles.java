package com.art5019.afrobrazilities.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;

@Mod(MODID)
public class Candles {

    //Block TWO_TONE_RED_AND_BLACK_CANDLE_BLOCK = register(CandleBlock::new, candleProperties(MapColor.COLOR_RED));

    public static final DeferredBlock<Block> TWO_TONE_RED_AND_BLACK_CANDLE = BLOCKS.register("two_tone_red_and_black_candle", x -> new CandleBlock(candleProperties(MapColor.COLOR_RED,x)));

    private static BlockBehaviour.Properties candleProperties(MapColor mapColor,ResourceLocation name) {
        return BlockBehaviour.Properties.of()
                .mapColor(mapColor)
                .noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, name))
                .strength(0.1F)
                .sound(SoundType.CANDLE)
                .lightLevel(CandleBlock.LIGHT_EMISSION)
                .pushReaction(PushReaction.DESTROY);
    }

    public static final DeferredItem<BlockItem> TWO_TONE_RED_AND_BLACK_CANDLE_ITEM = ITEMS.registerSimpleBlockItem(
            "two_tone_red_and_black_candle",
            TWO_TONE_RED_AND_BLACK_CANDLE,
            new Item.Properties()
    );
}
