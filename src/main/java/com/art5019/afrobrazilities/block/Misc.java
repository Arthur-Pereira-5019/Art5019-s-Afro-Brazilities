package com.art5019.afrobrazilities.block;

import com.art5019.afrobrazilities.Art5019sAfrobrazilities;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.BLOCKS;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static net.minecraft.world.level.block.Blocks.CAULDRON;

@Mod(MODID)
public class Misc {
    public static final DeferredBlock<Block> SOY_OIL_CAULDRON = BLOCKS.register("soy_oil_cauldron", (x) -> new LayeredCauldronBlock(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, cauldronProperties(x)));
    public static final DeferredBlock<Block> SOY_FEED_CAULDRON = BLOCKS.register("soy_feed_cauldron", (x) -> new LayeredCauldronBlock(Biome.Precipitation.NONE, CauldronInteraction.EMPTY, cauldronProperties(x)));

    private static BlockBehaviour.Properties cauldronProperties(ResourceLocation name) {
        return BlockBehaviour.Properties.of().mapColor(MapColor.STONE).
                requiresCorrectToolForDrops().strength(2.0F).noOcclusion()
                .setId(ResourceKey.create(Registries.BLOCK, name));

    }
}
