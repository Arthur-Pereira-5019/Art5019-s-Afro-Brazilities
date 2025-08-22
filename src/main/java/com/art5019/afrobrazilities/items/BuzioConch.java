package com.art5019.afrobrazilities.items;

import com.art5019.afrobrazilities.Art5019sAfrobrazilities;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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
