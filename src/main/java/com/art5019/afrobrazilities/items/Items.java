package com.art5019.afrobrazilities.items;

import com.art5019.afrobrazilities.block.Orchid;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.*;
import static com.art5019.afrobrazilities.block.Simple_Plants.makeEffect;
import static com.art5019.afrobrazilities.block.Simple_Plants.plant_properties;

@Mod(MODID)
public class Items {
    public static final DeferredItem<Item> BUZIO_CONCH = ITEMS.registerItem(
            "buzio_conch",
            Item::new,
            new Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<Item> BASE_DYE = ITEMS.registerItem(
            "base_dye",
            Item::new,
            new Properties()
    );


}
