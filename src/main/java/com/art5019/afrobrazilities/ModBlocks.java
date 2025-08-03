package com.art5019.afrobrazilities;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.BLOCKS;

@Mod(Art5019sAfrobrazilities.MODID)
public class ModBlocks {
    public static final DeferredBlock<Block> TEST_BLOCK = BLOCKS.register(
            "test_block",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.HONEY_BLOCK)
                    .lightLevel(state -> 7)
            ));
}
