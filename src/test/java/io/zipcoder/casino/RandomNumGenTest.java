package io.zipcoder.casino;

import io.zipcoder.casino.game.chancegames.chancegameutilities.RandomNumGen;
import org.junit.Assert;
import org.junit.Test;

public class RandomNumGenTest {

    @Test
    public void randomRouletteTest() {
        RandomNumGen gen = new RandomNumGen();

        int actual = gen.randomRoulette();

        Assert.assertTrue(actual >= 0 && actual<= 37);
    }

    @Test
    public void randomRouletteChangeTest() {
        RandomNumGen gen = new RandomNumGen();

        int first = gen.randomRoulette();
        int second = gen.randomRoulette();

        Assert.assertFalse(first == second);

    }
}
