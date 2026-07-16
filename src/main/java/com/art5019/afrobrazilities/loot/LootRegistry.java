package com.art5019.afrobrazilities.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.GLOBAL_LOOT_MODIFIER_SERIALIZERS;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class LootRegistry {
    public static final Supplier<MapCodec<BarteringModifier>> BARTERING_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("bartering_modifier", () -> BarteringModifier.CODEC);

}
