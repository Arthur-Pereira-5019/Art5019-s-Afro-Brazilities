package com.art5019.afrobrazilities.utils;

import net.minecraft.world.entity.player.Player;

import static com.art5019.afrobrazilities.data.DataAttachments.SPIRITUAL_PROTECTION;

public class SpiritualProtectionHelper {
    public static float testSpiritualProtection(Player p) {
        int b = p.getData(SPIRITUAL_PROTECTION);
        return b;
    }
}
