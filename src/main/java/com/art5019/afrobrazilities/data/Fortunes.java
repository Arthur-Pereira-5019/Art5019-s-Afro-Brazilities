package com.art5019.afrobrazilities.data;

public enum Fortunes {
    //LIMIT = 127
    NOTHING(0, "art5019afrobrazilities.fortune.full.nothing", "art5019afrobrazilities.fortune.name.nothing", 6, true),
    LUCK_FISHING(1, "art5019afrobrazilities.fortune.full.luck_fishing", "art5019afrobrazilities.fortune.name.luck_fishing",12,true),
    UNLUCK_FISHING(2, "art5019afrobrazilities.fortune.full.bad_fishing", "art5019afrobrazilities.fortune.name.bad_fishing",6,false),
    LUCK_FIGHT(3, "art5019afrobrazilities.fortune.full.fight_luck", "art5019afrobrazilities.fortune.name.fight_luck",16,true),
    UNLUCK_FIGHT(4, "art5019afrobrazilities.fortune.full.unluck_fight", "art5019afrobrazilities.fortune.name.unluck_fight",16,false),
    DEATH(5, "art5019afrobrazilities.fortune.full.death", "art5019afrobrazilities.fortune.name.death",1,false),
    LUCK_INCREASE(6, "art5019afrobrazilities.fortune.full.luck_increase", "art5019afrobrazilities.fortune.name.luck_increase",8,true),
    UNLUCK_INCREASE(7, "art5019afrobrazilities.fortune.full.unluck_increase", "art5019afrobrazilities.fortune.name.unluck_increase",8,false),
    MESSAGE_FROM_YOUR_GUIDES(8, "art5019afrobrazilities.fortune.full.message", "art5019afrobrazilities.fortune.name.message",7,true),
    GUARANTEED_TRIUMPH(9, "art5019afrobrazilities.fortune.full.guaranteed_triumph", "art5019afrobrazilities.fortune.name.guaranteed_triumph",5,true),
    GUARANTEED_FAIL(10, "art5019afrobrazilities.fortune.full.guaranteed_fail", "art5019afrobrazilities.fortune.name.guaranteed_fail",6,false),
    HEALTH_PROBLEM(11, "art5019afrobrazilities.fortune.full.health_problem", "art5019afrobrazilities.fortune.name.health_problem",6,false),
    EXTRA_PROFITS(12, "art5019afrobrazilities.fortune.full.extra_profits", "art5019afrobrazilities.fortune.name.extra_profits",7,true),
    STRONG_ENEMY(13, "art5019afrobrazilities.fortune.full.strong_enemy", "art5019afrobrazilities.fortune.name.strong_enemy", 3, false);

    private final int id;
    private final String tk_complete;
    private final String tk_name;
    private final int weigth;
    private final boolean positive;

    Fortunes(int id, String tk_complete, String tk_name, int weigth, boolean positive) {
        this.id = id;
        this.tk_complete = tk_complete;
        this.tk_name = tk_name;
        this.weigth = weigth;
        this.positive = positive;
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

    public int getWeigth() {
        return weigth;
    }

    public boolean isPositive() {
        return positive;
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
