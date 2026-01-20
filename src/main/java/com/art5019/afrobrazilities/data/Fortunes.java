package com.art5019.afrobrazilities.data;

public enum Fortunes {
    NOTHING(0),
    LUCK_FISHING(1),
    UNLUCK_FISHING(2),
    LUCK_FIGHT(3),
    UNLUCK_FIGHT(4),
    DEATH(5),
    LUCK_INCREASE(6),
    UNLUCK_INCREASE(7),
    MESSAGE_FROM_YOUR_GUIDES(8),
    GUARANTEED_TRIUMPH(9),
    GUARANTEED_FAIL(10),
    HEALTH_PROBLEM(11),
    EXTRA_PROFITS(12),
    STRONG_ENEMY(13);

    private final int id;
    Fortunes(int id) {
        this.id = id;
    }
}
