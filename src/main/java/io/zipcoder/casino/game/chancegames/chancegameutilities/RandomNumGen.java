package io.zipcoder.casino.game.chancegames.chancegameutilities;

import java.util.Random;

public class RandomNumGen {
Random random;

    public RandomNumGen() {
        random = new Random();
    }


    public int randomRoulette() {
        return random.nextInt(37);
    }
}
