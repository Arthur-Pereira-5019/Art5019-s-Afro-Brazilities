package com.art5019.afrobrazilities.utils;

import net.minecraft.world.entity.player.Player;

import static com.art5019.afrobrazilities.data.DataAttachments.*;

public class SpiritualHelper {
    public static float testSpiritualProtection(Player p) {
        int b = p.getData(SPIRITUAL_PROTECTION);
        return b;
    }

    public static float testKarma(Player p) {
        int b = p.getData(KARMA);
        return b;
    }

    public static float testAse(Player p) {
        float ase = 0;
        ase += p.getLuck();
        ase += p.getData(ASE)/4.0F;
        ase += p.getData(KARMA)/10.0F;
        return ase;
    }

    public static void addAse(Player p, int a) {
        p.setData(ASE, p.getData(ASE) +a);
    }
}
