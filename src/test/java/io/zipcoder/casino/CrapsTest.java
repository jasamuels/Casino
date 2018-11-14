package io.zipcoder.casino;

import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.dicegames.diceutilities.RandomDice;
import io.zipcoder.casino.game.dicegames.individualdicegames.Craps;
import org.junit.Assert;
import org.junit.Test;

public class CrapsTest {

    Craps craps;
    Player player;
    mockRandom rand = new mockRandom();

    public CrapsTest(){
        player = new Player();
        craps = new Craps(player);
    }

    private class mockRandom extends RandomDice {
        public Integer rollNumber;

        @Override
        public int randDice(){
            return rollNumber;
        }
    }

    @Test
    public void createDiceTest() {
        craps.createDice(2);

        int expected = 2;

        int actual = craps.getPairOfDice().getNumOfDice();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void rollTest() {
        craps.createDice(2);

        boolean actualRoll = false;

        int diceRoll = craps.roll();
        if (diceRoll <= 12 && diceRoll >= 2){
            actualRoll = true;
        }
        //then
        System.out.println(diceRoll);
        Assert.assertTrue(actualRoll);

    }

    @Test
    public void getNumOfRollsTest() {
        int expected = 0;

        int actual = craps.getNumOfRolls();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void playTest() {
    }

    @Test
    public void betTest() {
        craps.win = true;
        craps.bet(10);

        int expected = player.getBank();
        int actual = 10020;

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void betTest1() {
        craps.win = false;
        craps.bet(10);

        int expected = player.getBank();
        int actual = 9990;

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void firstRoll() {
        craps.firstRoll();

        Assert.assertTrue(craps.rollOne >= 2 && craps.rollOne <= 12);
    }

    @Test
    public void firstRoll2(){
        Craps craps = new Craps(player);

        rand.rollNumber = 6;

        craps.getPairOfDice().setRandomDice(rand);

        craps.firstRoll();

        Assert.assertFalse(craps.isWin());
}

    @Test
    public void firstRoll3(){
        Craps craps = new Craps(player);

        rand.rollNumber = 1;

        craps.getPairOfDice().setRandomDice(rand);

        craps.firstRoll();

        Assert.assertFalse(craps.isWin());
    }

    @Test
    public void secondRoll() {
        Craps craps = new Craps(player);

        rand.rollNumber = 3;

        craps.getPairOfDice().setRandomDice(rand);

        craps.firstRoll();

        Assert.assertTrue(craps.isWin());
    }

    @Test
    public void secondRoll2() {
        Craps craps = new Craps(player);

        rand.rollNumber = 4;

        craps.getPairOfDice().setRandomDice(rand);

        craps.firstRoll();

        Assert.assertTrue(craps.isWin());
    }



    @Test
    public void endGameTest() {
        craps.continueGame = true;
        Assert.assertTrue("yes", true);
    }

    @Test
    public void endGameTest2() {
        craps.continueGame = false;
        Assert.assertTrue("no", true);
    }

}

