package com.art5019.afrobrazilities.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Misc.SOY_FEED_ITEM;
import static com.art5019.afrobrazilities.data.DataAttachments.STOCK_FAT;
import static net.minecraft.world.item.Items.WRITABLE_BOOK;

@Mod(MODID)
@EventBusSubscriber
public class PlayerRightClickEntity {

    @SubscribeEvent
    public static void playerRightClicksEntity(PlayerInteractEvent.EntityInteract event) {
        if(event.getTarget().getTags().contains("art5019safrobrazilities:live_stock")) {
            stockFeed(event.getEntity(), event.getLevel(), event.getTarget());
        }
    }

    public static void stockFeed(Player player, Level level, Entity entity) {
        if(player.isHolding(SOY_FEED_ITEM.asItem())) {
            entity.setData(STOCK_FAT, entity.getData(STOCK_FAT)+0.13F);
            player.getInventory().clearOrCountMatchingItems(p -> SOY_FEED_ITEM.asItem() == p.getItem(),1, player.inventoryMenu.getCraftSlots());
            level.playSound(null, player.blockPosition(), SoundEvents.GENERIC_EAT.value(), SoundSource.NEUTRAL, 1F, 1);
        }
    }
}
