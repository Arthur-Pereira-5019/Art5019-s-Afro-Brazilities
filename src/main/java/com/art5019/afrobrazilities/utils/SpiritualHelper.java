package com.art5019.afrobrazilities.utils;

import net.minecraft.world.entity.player.Player;

import static com.art5019.afrobrazilities.data.DataAttachments.KARMA;
import static com.art5019.afrobrazilities.data.DataAttachments.SPIRITUAL_PROTECTION;

public class SpiritualHelper {
    public static float testSpiritualProtection(Player p) {
        int b = p.getData(SPIRITUAL_PROTECTION);
        return b;
    }

    public static float testKarma(Player p) {
        int b = p.getData(KARMA);
        return b;
    }
}
