package com.art5019.afrobrazilities.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Objects;
import java.util.function.Function;

@Mixin(Blocks.class)
public class BlocksMixin {
    @ModifyVariable(method = "register(Ljava/lang/String;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", at = @At("HEAD"), argsOnly = true)
    private static BlockBehaviour.Properties registerPropertyHandler(BlockBehaviour.Properties properties, String name, Function<BlockBehaviour.Properties, Block> factory) {
        if(Objects.equals(name, "dandelion")) {
            return properties.randomTicks();
        }
        return properties;
    }
}


