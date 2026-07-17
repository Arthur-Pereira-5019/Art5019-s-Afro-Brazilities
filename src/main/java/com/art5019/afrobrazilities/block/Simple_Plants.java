package com.art5019.afrobrazilities.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.List;
import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.propertiesCodec;
import static net.minecraft.world.level.block.state.BlockBehaviour.simpleCodec;

@Mod(MODID)
public class Simple_Plants {
    public static final DeferredBlock<Block> GROWN_DANDELION = BLOCKS.register("grown_dandelion", x -> new GrownDandelion(makeEffect(MobEffects.SATURATION, 0.7F), plant_properties(x)));
    public static final DeferredBlock<Block> WHITE_ORCHID = BLOCKS.register("white_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), plant_properties(x)));
    public static final DeferredBlock<Block> WINE_ORCHID = BLOCKS.register("wine_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), plant_properties(x)));
    public static final DeferredBlock<Block> YELLOW_ORCHID = BLOCKS.register("yellow_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), plant_properties(x)));
    public static final DeferredBlock<Block> LILAC_ORCHID = BLOCKS.register("lilac_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), plant_properties(x)));
    public static final DeferredBlock<Block> PURPLE_ORCHID = BLOCKS.register("purple_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), plant_properties(x)));
    public static final DeferredBlock<Block> STARRY_ORCHID = BLOCKS.register("starry_orchid", x -> new Orchid(makeEffect(MobEffects.GLOWING, 10F), plant_properties(x)));
    public static final DeferredBlock<Block> RAINBOW_ORCHID = BLOCKS.register("rainbow_orchid", x -> new Orchid(makeEffect(MobEffects.GLOWING, 7F), plant_properties(x)));
    public static final DeferredBlock<Block> BEANS = BLOCKS.register("beans", x -> new BeanCrop(plant_properties(x)));
    public static final DeferredBlock<Block> DANDELION_SPROUT = BLOCKS.register("dandelion_sprout", x -> new DandelionSprout(makeEffect(MobEffects.SATURATION, 0.1F), plant_properties(x)));

    private static BlockBehaviour.Properties plant_properties(ResourceLocation name) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .instabreak()
                .setId(ResourceKey.create(Registries.BLOCK, name))
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
                .randomTicks();
    }

    public static final DeferredItem<BlockItem> GROWN_DANDELION_ITEM = ITEMS.registerSimpleBlockItem(
            "grown_dandelion",
            GROWN_DANDELION,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> DANDELION_SPROUT_ITEM = ITEMS.registerSimpleBlockItem(
            "dandelion_sprout",
            DANDELION_SPROUT,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> BEANS_ITEM = ITEMS.registerSimpleBlockItem(
            "beans",
            BEANS,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> WHITE_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "white_orchid",
            WHITE_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> YELLOW_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "yellow_orchid",
            YELLOW_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> LILAC_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "lilac_orchid",
            LILAC_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> PURPLE_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "purple_orchid",
            PURPLE_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> WINE_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "wine_orchid",
            WINE_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> RAINBOW_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "rainbow_orchid",
            RAINBOW_ORCHID,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> STARRY_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "starry_orchid",
            STARRY_ORCHID,
            new Item.Properties()
    );



    public static final Supplier<MapCodec<DandelionSprout>> DANDELION_SPROUT_CODEC = BLOCK_TYPES.register(
            "dandelion_sprout",
            () -> RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                        FlowerBlock.EFFECTS_FIELD.forGetter(FlowerBlock::getSuspiciousEffects), propertiesCodec()).apply(instance, DandelionSprout::new)
                    )
    );

    public static final Supplier<MapCodec<GrownDandelion>> GROWN_DANDELION_CODEC = BLOCK_TYPES.register(
            "grown_dandelion",
            () -> RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                        FlowerBlock.EFFECTS_FIELD.forGetter(FlowerBlock::getSuspiciousEffects), propertiesCodec()).apply(instance, GrownDandelion::new)
                    )
    );

    public static final Supplier<MapCodec<Orchid>> ORCHID_CODEC = BLOCK_TYPES.register(
            "orchid",
            () -> RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                        FlowerBlock.EFFECTS_FIELD.forGetter(FlowerBlock::getSuspiciousEffects), propertiesCodec()).apply(instance, Orchid::new)
                    )
    );

    public static final Supplier<MapCodec<BeanCrop>> BEAN_CODEC = BLOCK_TYPES.register(
            "beans",
            () -> simpleCodec(BeanCrop::new)
    );

    protected static SuspiciousStewEffects makeEffect(Holder<MobEffect> effect, float seconds) {
        return new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, Mth.floor(seconds * 20.0F))));
    }

}
