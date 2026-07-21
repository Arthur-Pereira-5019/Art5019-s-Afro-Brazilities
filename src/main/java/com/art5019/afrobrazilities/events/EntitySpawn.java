package com.art5019.afrobrazilities.events;

import com.art5019.afrobrazilities.items.Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Simple_Plants.*;

@Mod(MODID)
@EventBusSubscriber
public class EntitySpawn {
    @SubscribeEvent
    private static void onEntitySpawn(FinalizeSpawnEvent event) {
        Random r = new Random();
        LivingEntity entity = event.getEntity();
        if(entity instanceof Drowned) {
            if(!event.getLevel().isClientSide()) {
                if(r.nextInt(1,10) < 4) {
                    entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BUZIO_CONCH.asItem()));
                }
            }
        }
        if(entity instanceof WanderingTrader) {
            WanderingTrader villager = (WanderingTrader) entity;
            if(r.nextInt(0,100) < 3) {
                villager.addOffersFromItemListings(villager.getOffers(),(new VillagerTrades.ItemListing[]
                        {new VillagerTrades.ItemsAndEmeraldsToItems(BLACK_ORCHID_ITEM.asItem(), 1, 29, RAINBOW_ORCHID_ITEM.asItem(), 1, 3, 1, 2),new VillagerTrades.ItemsAndEmeraldsToItems(BLACK_ORCHID_ITEM.asItem(), 1, 29, STARRY_ORCHID_ITEM.asItem(), 1, 3, 1, 2)}), 1);
            }
        }
    }

}
