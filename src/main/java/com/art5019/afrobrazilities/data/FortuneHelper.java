package com.art5019.afrobrazilities.data;

import java.util.List;
import java.util.Map;

public class FortuneHelper {
    private static int totalWeight;
    private static List<Fortunes> fortunes;
    public static void generateFortunes() {
    }

    public static void getBase() {
        fortunes = List.of(Fortunes.values());
        int x = 0;
        for(Fortunes f: fortunes) {
            x+= f.getWeigth();
        }
        totalWeight = x;
    }


}
