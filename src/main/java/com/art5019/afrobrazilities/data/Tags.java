package com.art5019.afrobrazilities.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.neoforged.fml.common.Mod;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class Tags {
    public static final TagKey<EntityType<?>> LIVE_STOCK = TagKey.create(
            Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath("art5019safrobrazilities", "live_stock")
    );
}
