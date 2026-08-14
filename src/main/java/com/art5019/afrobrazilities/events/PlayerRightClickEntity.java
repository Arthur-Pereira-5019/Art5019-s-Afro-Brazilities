package com.art5019.afrobrazilities.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;
import static com.art5019.afrobrazilities.block.Misc.SOY_FEED_ITEM;
import static com.art5019.afrobrazilities.data.DataAttachments.STOCK_FAT;
import static com.art5019.afrobrazilities.data.Tags.LIVE_STOCK;
import static net.minecraft.world.item.Items.WRITABLE_BOOK;

@Mod(MODID)
@EventBusSubscriber
public class PlayerRightClickEntity {

    static ResourceLocation animal_fat = ResourceLocation.fromNamespaceAndPath("art5019safrobrazilities", "animal_fat");

    @SubscribeEvent
    public static void playerRightClicksEntity(PlayerInteractEvent.EntityInteract event) {
        if(event.getLevel() instanceof ServerLevel && event.getTarget() instanceof LivingEntity) {
            if(event.getTarget().getType().is(LIVE_STOCK)) {
                stockFeed(event.getEntity(), event.getLevel(), (LivingEntity) event.getTarget());
            }
        }
    }

    public static void stockFeed(Player player, Level level, LivingEntity entity) {
        if(player.isHolding(SOY_FEED_ITEM.asItem())) {
            entity.setData(STOCK_FAT, entity.getData(STOCK_FAT)+0.13F);
            player.getInventory().clearOrCountMatchingItems(p -> SOY_FEED_ITEM.asItem() == p.getItem(),1, player.inventoryMenu.getCraftSlots());
            level.playSound(null, player.blockPosition(), SoundEvents.GENERIC_EAT.value(), SoundSource.NEUTRAL, 1F, 1);
            modifyFat(entity);
        }
    }


    public static void modifyFat(LivingEntity entity) {
        double value = 0.05;
        AttributeInstance instance = entity.getAttributes().getInstance(Attributes.SCALE);
        if(instance.hasModifier(animal_fat)) {
            value = instance.getModifier(animal_fat).amount()+0.05F;
        }
        instance.addOrUpdateTransientModifier(new AttributeModifier(animal_fat,value, AttributeModifier.Operation.ADD_VALUE));
    }
}
