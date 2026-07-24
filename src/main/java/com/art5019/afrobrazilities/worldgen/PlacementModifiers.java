package com.art5019.afrobrazilities.worldgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.PLACEMENT_MODIFIERS;

@Mod(MODID)
public class PlacementModifiers {
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<HumidityFilter>> HUMIDITY = register("humidity_filter", HumidityFilter.CODEC);
    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<TemperatureFilter>> TEMPERATURE = register("temperature_filter", TemperatureFilter.CODEC);

    private static <T extends PlacementModifier> DeferredHolder<PlacementModifierType<?>, PlacementModifierType<T>> register(String id, MapCodec<T> codec) {
        return PLACEMENT_MODIFIERS.register(id, () -> () -> codec);
    }

}
