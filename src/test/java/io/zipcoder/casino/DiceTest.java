package io.zipcoder.casino;

import io.zipcoder.casino.game.dicegames.diceutilities.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void DiceTest1(){

        Dice dice = new Dice(2);

        boolean actual = false;
        int roll = dice.tossAndSum();
        if (roll <= 12 && roll >= 1){
            actual = true;
        }
        System.out.println(roll);
        Assert.assertTrue(actual);
    }
}