package com.art5019.afrobrazilities.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.ITEMS;
import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.MODID;

@Mod(MODID)
public class BuzioConch {
    public static final DeferredItem<Item> BUZIO_CONCH = ITEMS.registerItem(
            "buzio_conch",
            Item::new, // The factory that the properties will be passed into.
            new Properties().rarity(Rarity.UNCOMMON)
    );
}
