package com.art5019.afrobrazilities.data;

public enum Fortunes {
    NOTHING(0, "art5019afrobrazilities.fortune.full.nothing", "art5019afrobrazilities.fortune.name.nothing", 6, 0),
    LUCK_FISHING_1(1, "art5019afrobrazilities.fortune.full.luck_fishing", "art5019afrobrazilities.fortune.name.luck_fishing",12,3),
    UNLUCK_FISHING(2, "art5019afrobrazilities.fortune.full.bad_fishing", "art5019afrobrazilities.fortune.name.bad_fishing",6,-1),
    LUCK_FIGHT(3, "art5019afrobrazilities.fortune.full.fight_luck", "art5019afrobrazilities.fortune.name.fight_luck",16,3),
    UNLUCK_FIGHT(4, "art5019afrobrazilities.fortune.full.unluck_fight", "art5019afrobrazilities.fortune.name.unluck_fight",16,-2),
    DEATH(5, "art5019afrobrazilities.fortune.full.death", "art5019afrobrazilities.fortune.name.death",1,-10),
    LUCK_INCREASE(6, "art5019afrobrazilities.fortune.full.luck_increase", "art5019afrobrazilities.fortune.name.luck_increase",8,5),
    UNLUCK_INCREASE(7, "art5019afrobrazilities.fortune.full.unluck_increase", "art5019afrobrazilities.fortune.name.unluck_increase",8,-5),
    MESSAGE_FROM_YOUR_GUIDES(8, "art5019afrobrazilities.fortune.full.message", "art5019afrobrazilities.fortune.name.message",7,4),
    GUARANTEED_TRIUMPH(9, "art5019afrobrazilities.fortune.full.guaranteed_triumph", "art5019afrobrazilities.fortune.name.guaranteed_triumph",0,7),
    GUARANTEED_FAIL(10, "art5019afrobrazilities.fortune.full.guaranteed_fail", "art5019afrobrazilities.fortune.name.guaranteed_fail",0,-6),
    HEALTH_PROBLEM(11, "art5019afrobrazilities.fortune.full.health_problem", "art5019afrobrazilities.fortune.name.health_problem",6,-8),
    EXTRA_PROFITS(12, "art5019afrobrazilities.fortune.full.extra_profits", "art5019afrobrazilities.fortune.name.extra_profits",7,4),
    STRONG_ENEMY(13, "art5019afrobrazilities.fortune.full.strong_enemy", "art5019afrobrazilities.fortune.name.strong_enemy", 30, -4),
    JUDGEMENT(14, "art5019afrobrazilities.fortune.full.judgement", "art5019afrobrazilities.fortune.name.judgement", 16, 0),
    MINING_LUCK(15, "art5019afrobrazilities.fortune.full.mining_luck", "art5019afrobrazilities.fortune.name.mining_luck", 6, 6),
    LUCK_FISHING_2(17, "art5019afrobrazilities.fortune.full.luck_fishing_2", "art5019afrobrazilities.fortune.name.luck_fishing_2",12,3),
    BETRAYAL(18, "art5019afrobrazilities.fortune.full.betrayal", "art5019afrobrazilities.fortune.name.betrayal",4,-6);

    private final int id;
    private final String tk_complete;
    private final String tk_name;
    private final int weight;
    private final int positiveness;

    Fortunes(int id, String tk_complete, String tk_name, int weigth, int positiveness) {
        this.id = id;
        this.tk_complete = tk_complete;
        this.tk_name = tk_name;
        this.weight = weigth;
        this.positiveness = positiveness;
    }

    public int getId() {
        return id;
    }

    public String getTk_complete() {
        return tk_complete;
    }

    public String getTk_name() {
        return tk_name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPositiveness() {
        return positiveness;
    }

    public static Fortunes getById(Integer id) {
        for (Fortunes f : values()) {
            if (f.id == id) {
                return f;
            }
        }
        return NOTHING;
    }
}
