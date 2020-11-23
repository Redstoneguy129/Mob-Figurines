package me.redstoneguy129.mod.mobfigurine;

import java.util.Random;

public enum FigurineRarity {
    COMMON(80),
    RARE(20);

    private final int percentage;

    FigurineRarity(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public static FigurineRarity CommonOrRare(Random random) {
        return random.nextInt(100) > FigurineRarity.RARE.getPercentage() ? FigurineRarity.COMMON : FigurineRarity.RARE;
    }
}
