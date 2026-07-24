package com.art5019.afrobrazilities.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.apache.commons.io.function.IOQuadFunction;

public class TemperatureFilter extends PlacementFilter {
    public static MapCodec<TemperatureFilter> CODEC = Codec.FLOAT.fieldOf("temperature").xmap(TemperatureFilter::new, (x) -> x.temperature);

    private final float temperature;

    public TemperatureFilter(float temperature) {
        this.temperature = temperature;
    }

    @Override
    protected boolean shouldPlace(PlacementContext placementContext, RandomSource randomSource, BlockPos blockPos) {
        int i = QuartPos.fromBlock(blockPos.getX());
        int j = QuartPos.fromBlock(blockPos.getY());
        int k = QuartPos.fromBlock(blockPos.getZ());
        var result = placementContext.getLevel().getLevel().getChunkSource().randomState().sampler().sample(i,j,k);
        float ct = Climate.unquantizeCoord(result.temperature());
        if(ct > temperature) {
            return true;
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> type() {
        return PlacementModifiers.TEMPERATURE.get();
    }
}
