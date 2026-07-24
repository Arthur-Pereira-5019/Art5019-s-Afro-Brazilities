package com.art5019.afrobrazilities.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.placement.*;
import org.apache.commons.io.function.IOQuadFunction;

public class HumidityFilter extends PlacementFilter {
    public static MapCodec<HumidityFilter> CODEC = Codec.FLOAT.fieldOf("humidity").xmap(HumidityFilter::new, (x) -> x.humidity);

    private final float humidity;

    public HumidityFilter(float humidity) {
        this.humidity = humidity;
    }

    @Override
    protected boolean shouldPlace(PlacementContext placementContext, RandomSource randomSource, BlockPos blockPos) {
        WorldGenLevel level = placementContext.getLevel();
        RegistryAccess registryaccess = level.registryAccess();
        RandomState rs = RandomState.create(NoiseGeneratorSettings.dummy(), registryaccess.lookupOrThrow(Registries.NOISE), placementContext.getLevel().getSeed());
        var result = rs.sampler().sample(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        IOQuadFunction<Climate.Sampler, Integer, Integer, Integer, Climate.TargetPoint> sample = Climate.Sampler.sample(blockPos.getX(),blockPos.getY(),blockPos.getZ());
        System.out.println(result.humidity());
        if(result.humidity() > humidity) {
            return true;
        }
        return false;
        level.getChunkSource().randomState().sampler()
    }

    @Override
    public PlacementModifierType<?> type() {
        return PlacementModifiers.HUMIDITY.get();
    }
}
