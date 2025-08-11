package com.art5019.afrobrazilities.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class EntitySpawn {
    @SubscribeEvent
    private static void onEntitySpawn(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
    }
}
