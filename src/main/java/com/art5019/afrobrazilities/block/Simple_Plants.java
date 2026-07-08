package com.art5019.afrobrazilities.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;

@Mod(MODID)
public class Simple_Plants {
    public static final DeferredBlock<Block> GROWN_DANDELION = BLOCKS.register("grown_dandelion", x -> new FlowerBlock(MobEffects.SATURATION, 0.7F, plant_properties(x)));
    public static final DeferredBlock<Block> DANDELION_SPROUT = BLOCKS.register("dandelion_sprout", x -> new FlowerBlock(MobEffects.SATURATION, 0.1F, plant_properties(x)));

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


    public static final Supplier<MapCodec<DandelionSprout>> DANDELION_SPROUT_CODEC = BLOCK_TYPES.register(
            "simple",
            () -> RecordCodecBuilder.mapCodec(instance ->
                    instance.group(
                            Codec.
                            BlockBehaviour.propertiesCodec() // represents the BlockBehavior.Properties parameter
                    ).apply(instance, DandelionSprout::new)
            )
    );

}
