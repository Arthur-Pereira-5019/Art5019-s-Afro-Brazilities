package com.art5019.afrobrazilities.utils;

import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

import java.util.Comparator;
import java.util.List;

import static com.art5019.afrobrazilities.data.DataAttachments.FORTUNE;

public class GuideHelper {
    public static void messageProvider(Player p) {
        reveal(p,Component.literal("[???] "));
    }

    public static void reveal(Player p, MutableComponent name) {
        List<FortuneRecord> data = p.getData(FORTUNE);
        data.sort(Comparator.comparing(FortuneRecord::day));
        FortuneRecord fr = data.getLast();
        p.displayClientMessage(name.append(Component.translatable(Fortunes.getById(fr.fortune()).getTk_complete(),fr.day())),false);
    }
}
