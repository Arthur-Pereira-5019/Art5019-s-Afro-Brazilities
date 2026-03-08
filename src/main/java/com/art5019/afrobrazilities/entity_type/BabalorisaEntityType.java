package com.art5019.afrobrazilities.entity_type;

import com.art5019.afrobrazilities.entities.Babalorisa;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.fml.common.Mod;

import java.util.function.Supplier;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.ENTITY_TYPES;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class BabalorisaEntityType {

    public static final Supplier<EntityType<Babalorisa>> BABALORISA_TYPE = ENTITY_TYPES.register(
            "babalorisa",
            () -> EntityType.Builder.of(
                            Babalorisa::new,
                            MobCategory.MONSTER
                    )
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(ResourceKey.create(
                            Registries.ENTITY_TYPE,
                            ResourceLocation.fromNamespaceAndPath(MODID, "babalorisa")
                    ))
    );


}

