package com.art5019.afrobrazilities.data;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.Art5019sAfrobrazilities.LOGGER;
import static com.art5019.afrobrazilities.data.FortuneDataAttachment.FORTUNE;

public class FortuneHelper {
    private static int totalWeight;
    private static List<Fortunes> fortunes;
    private static Random r = new Random();
    public static void generateFortunes(int times, Player p, Level level) {
        int day = (int) level.getGameTime() / 24000;
        float ase = testAse(p);
        for(int i = 0; i < times; i++) {
            Fortunes f;
            if(Math.abs(ase) > 3) {
                f = optimalOutcome(p,(int) Math.floor(ase));
            } else {
                f = generateRandomFortune(p);
            }
            var data = p.getData(FORTUNE);
            data.add(new FortuneRecord(f.getId(), day + r.nextInt(2, 90), false));
            p.setData(FORTUNE, data);
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

    private static float testAse(Player p) {
        float ase = 0;
        ase += p.getLuck();
        return ase;
    }

    private static Fortunes optimalOutcome(Player p, int ase) {
        int iase = ase/4;
        List<Fortunes> fortunesList = new ArrayList<>();
        for(int i = 0; i < iase; i++) {
            fortunesList.add(generateRandomFortune(p));
        }
        for(int i = 0; i < iase; i++) {
            if(ase > 0) {
                fortunesList.sort(Comparator.comparing(Fortunes::getPositiveness));
            } else {
                fortunesList.sort(Comparator.comparing(Fortunes::getPositiveness).reversed());
            }
        }
        return fortunesList.getFirst();
    }

    private static Fortunes generateRandomFortune(Player p) {
        int l = r.nextInt(0,totalWeight);
        int cW = 0;
        for (Fortunes fortune : fortunes) {
            if (cW > l) {
                var data = p.getData(FORTUNE);
                return fortune;
            }
            cW += fortune.getWeigth();
        }
        return Fortunes.NOTHING;
    }


}
