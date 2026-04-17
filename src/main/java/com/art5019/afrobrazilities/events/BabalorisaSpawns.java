package com.art5019.afrobrazilities.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.Villager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.entity_type.BabalorisaEntityType.BABALORISA_TYPE;

@Mod(MODID)
@EventBusSubscriber
public class BabalorisaSpawns {
    @SubscribeEvent // on the mod event bus
    public static void createDefaultAttributes(EntityAttributeCreationEvent event) {
        event.put(
                BABALORISA_TYPE.get(),
                Villager.createLivingAttributes()
                        .add(Attributes.MAX_HEALTH, 20)
                        .add(Attributes.FOLLOW_RANGE,16)
                        .build()
        );
    }
}
