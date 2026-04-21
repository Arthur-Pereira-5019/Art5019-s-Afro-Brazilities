package com.art5019.afrobrazilities.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.capabilities.Capabilities;

public class ItemStackUtils {
    public static boolean is(ItemStack c1, Item c2, int quantity) {
        return c1.is(c2) && c1.getCount() >= quantity;
    }

    public static void remove(Player p, Item item, int q) {
        p.getInventory().getNonEquipmentItems().forEach(i -> {
            if(i.is(item)) {

            }
        });
    }
}
