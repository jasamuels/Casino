package io.zipcoder.casino.game.dicegames.diceutilities;

import java.util.Random;

public class RandomDice {

    public int randDice()

    {
        int sum = 0;
        Random rand = new Random();
        sum = rand.nextInt(6) + 1;

        return sum;
    }
}
