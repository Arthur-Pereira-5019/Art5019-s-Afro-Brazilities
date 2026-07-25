package com.art5019.afrobrazilities.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.level.block.FlowerBlock;

import java.util.List;
import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;
import static com.art5019.afrobrazilities.items.Items.BASE_DYE;
import static net.minecraft.world.item.Items.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.propertiesCodec;
import static net.minecraft.world.level.block.state.BlockBehaviour.simpleCodec;

@Mod(MODID)
public class Simple_Plants {

    public static final DeferredBlock<Orchid> RAINBOW_ORCHID = BLOCKS.register("rainbow_orchid", x -> new Orchid(makeEffect(MobEffects.GLOWING, 7F), orchid_properties(x),BASE_DYE));
    public static final DeferredBlock<Block> GROWN_DANDELION = BLOCKS.register("grown_dandelion", x -> new GrownDandelion(makeEffect(MobEffects.SATURATION, 0.7F), plant_properties(x)));
    public static final DeferredBlock<Orchid> WHITE_ORCHID = BLOCKS.register("white_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), orchid_properties(x),WHITE_DYE));
    public static final DeferredBlock<Orchid> WINE_ORCHID = BLOCKS.register("wine_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), orchid_properties(x),MAGENTA_DYE));
    public static final DeferredBlock<Orchid> YELLOW_ORCHID = BLOCKS.register("yellow_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), orchid_properties(x),YELLOW_DYE));
    public static final DeferredBlock<Orchid> LILAC_ORCHID = BLOCKS.register("lilac_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), orchid_properties(x),PINK_DYE));
    public static final DeferredBlock<Orchid> PURPLE_ORCHID = BLOCKS.register("purple_orchid", x -> new Orchid(makeEffect(MobEffects.RESISTANCE, 8F), orchid_properties(x),PURPLE_DYE));
    public static final DeferredBlock<Orchid> STARRY_ORCHID = BLOCKS.register("starry_orchid", x -> new Orchid(makeEffect(MobEffects.GLOWING, 10F), orchid_properties(x),GLOW_INK_SAC));
    public static final DeferredBlock<Orchid> BLACK_ORCHID = BLOCKS.register("black_orchid", x -> new Orchid(makeEffect(MobEffects.DARKNESS, 7F), orchid_properties(x),BLACK_DYE));
    public static final DeferredBlock<Block> BLACK_BEANS = BLOCKS.register("black_beans", x -> new AbstractBeanCrop(plant_properties(x)));
    public static final DeferredBlock<Block> SOYBEANS = BLOCKS.register("soybeans", x -> new AbstractBeanCrop(plant_properties(x)));
    public static final DeferredBlock<Block> DANDELION_SPROUT = BLOCKS.register("dandelion_sprout", x -> new DandelionSprout(makeEffect(MobEffects.SATURATION, 0.1F), plant_properties(x)));

    public static final List<DeferredBlock<Orchid>> ORCHIDS = List.of(WHITE_ORCHID,WINE_ORCHID,YELLOW_ORCHID,LILAC_ORCHID,PURPLE_ORCHID,STARRY_ORCHID,RAINBOW_ORCHID,BLACK_ORCHID);

    public static BlockBehaviour.Properties plant_properties(ResourceLocation name) {
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

    public static BlockBehaviour.Properties orchid_properties(ResourceLocation name) {
        return BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .instabreak()
                .setId(ResourceKey.create(Registries.BLOCK, name))
                .sound(SoundType.GRASS)
                .offsetType(BlockBehaviour.OffsetType.XZ)
                .pushReaction(PushReaction.DESTROY)
                .randomTicks()
                .lightLevel(Orchid.LIGHT_EMISSION);
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

    public static final DeferredItem<BlockItem> BLACK_BEANS_ITEM = ITEMS.registerSimpleBlockItem(
            "black_beans",
            BLACK_BEANS,
            new Item.Properties()
    );

    public static final DeferredItem<BlockItem> SOYBEANS_ITEM = ITEMS.registerSimpleBlockItem(
            "soybeans",
            SOYBEANS,
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
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<BlockItem> STARRY_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "starry_orchid",
            STARRY_ORCHID,
            new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<BlockItem> BLACK_ORCHID_ITEM = ITEMS.registerSimpleBlockItem(
            "black_orchid",
            BLACK_ORCHID,
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

    /*public static final Supplier<MapCodec<Orchid>> ORCHID_CODEC = BLOCK_TYPES.register(
            "orchid",
            () -> RecordCodecBuilder.mapCodec((instance) ->
                    instance.group(
                        FlowerBlock.EFFECTS_FIELD.forGetter(FlowerBlock::getSuspiciousEffects), propertiesCodec(),Orchid::getDye).apply(instance, Orchid::new)
                    )
    );*/

    public static final Supplier<MapCodec<AbstractBeanCrop>> ABSTRACT_BEAN_CODEC = BLOCK_TYPES.register(
            "abstract_bean_codec",
            () -> simpleCodec(AbstractBeanCrop::new)
    );

    public static SuspiciousStewEffects makeEffect(Holder<MobEffect> effect, float seconds) {
        return new SuspiciousStewEffects(List.of(new SuspiciousStewEffects.Entry(effect, Mth.floor(seconds * 20.0F))));
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

}
