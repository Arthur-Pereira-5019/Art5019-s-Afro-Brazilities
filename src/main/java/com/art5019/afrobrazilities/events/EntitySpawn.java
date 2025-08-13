package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.Art5019sAfrobrazilities;
import com.art5019.afrobrazilities.items.BuzioConch;
import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.MobSpawnEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.ITEMS;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class EntitySpawn {
    @SubscribeEvent
    private static void onEntitySpawn(MobSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Drowned) {
            entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(BuzioConch.BUZIO_CONCH.asItem()));
        }
    }
}
