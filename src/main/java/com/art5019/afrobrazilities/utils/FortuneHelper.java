package com.art5019.afrobrazilities.utils;

import com.art5019.afrobrazilities.data.FortuneRecord;
import com.art5019.afrobrazilities.data.Fortunes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.art5019.afrobrazilities.data.DataAttachments.*;

public class FortuneHelper {
    private static int totalWeight;
    private static List<Fortunes> fortunes;
    private static Random r = new Random();
    public static void generateFortunes(int times, Player p, Level level) {
        ArrayList<FortuneRecord> data = new ArrayList<>(p.getData(FORTUNE));
        if(data.size() > 49) {
            return;
        }
        int day = getDay(level);
        float ase = testAse(p);
        for(int i = 0; i < times; i++) {
            Fortunes f;
            if(Math.abs(ase) > 3) {
                f = optimalOutcome(p,(int) Math.floor(ase));
            } else {
                f = generateRandomFortune(p);
            }
            data.add(new FortuneRecord(f.getId(), day + r.nextInt(1, 3), false));
            p.setData(FORTUNE, data);
        }

    }

    public static List<Fortunes> fortunesToday(Player p) {
        ArrayList<FortuneRecord> fortuneRecords = new ArrayList<>(p.getData(FORTUNE));
        int day = getDay(p.level());
        List<Fortunes> fortunes = new ArrayList<>();
        fortuneRecords.removeIf(f -> f.day() < day);
        p.setData(FORTUNE,fortuneRecords);
        fortuneRecords = new ArrayList<>(fortuneRecords.stream().filter(fortune -> fortune.day() == day).toList());
        fortuneRecords.forEach(f -> {fortunes.add(Fortunes.getById(f.fortune()));});
        return fortunes;
    }

    public static int getDay(Level level) {
        return (int) level.getGameTime() / 24000;
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
        ase += p.getData(ASE)/4.0F;
        ase += p.getData(KARMA)/10.0F;
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
            cW += fortune.getWeigth();
            if (cW > l) {
                return fortune;
            }
        }
        return Fortunes.NOTHING;
    }

    public static void removeFortuneOfToday(Player p, Fortunes f) {
        ArrayList<FortuneRecord> data = new ArrayList<>(p.getData(FORTUNE));
        data.removeIf(fr -> fr.day() == getDay(p.level()) && fr.fortune() == f.getId());
        p.setData(FORTUNE, data);
        generateFortunes(1,p, p.level());
    }

    public static void patchYesterdayFortunes(Player p) {
        ArrayList<FortuneRecord> data = new ArrayList<>(p.getData(FORTUNE));
        for(FortuneRecord fr: data) {
            if(fr.day() < getDay(p.level())) {
                data.remove(fr);
                generateFortunes(1,p, p.level());
            }
        }
        p.setData(FORTUNE, data);
    }




}
