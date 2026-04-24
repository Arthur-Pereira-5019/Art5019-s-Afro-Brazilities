package com.art5019.afrobrazilities.damages;

import net.minecraft.client.renderer.item.properties.numeric.Damage;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class DamageTypes {
    public static final ResourceKey<DamageType> PEACEFUL_DEATH =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "peaceful_death"));
    public static final ResourceKey<DamageType> PAINFUL_DEATH =
            ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "painful_death"));
}
