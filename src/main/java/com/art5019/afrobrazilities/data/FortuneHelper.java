package com.art5019.afrobrazilities.data;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.LOGGER;
import static com.art5019.afrobrazilities.data.FortuneDataAttachment.FORTUNE;

public class FortuneHelper {
    private static int totalWeight;
    private static List<Fortunes> fortunes;
    private static Random r = new Random();
    public static void generateFortunes(int t, Player p, Level level) {
        int day = (int) level.getGameTime() / 24000;
        for(int i = 0; i < t;i++) {
            int l = r.nextInt(0,totalWeight);
            int cW = 0;
            for (Fortunes fortune : fortunes) {
                if (cW > l) {
                    var data = p.getData(FORTUNE);
                    data.add(new FortuneRecord(fortune.getId(), day + r.nextInt(2, 90), false));
                    p.setData(FORTUNE, data);
                    LOGGER.warn(String.valueOf(i));
                    cW = 0;
                    break;
                }
                cW += fortune.getWeigth();
            }
        }

    }

    public static void startBase() {
        fortunes = List.of(Fortunes.values());
        int x = 0;
        for(Fortunes f: fortunes) {
            x+= f.getWeigth();
        }
        totalWeight = x;
    }


}
